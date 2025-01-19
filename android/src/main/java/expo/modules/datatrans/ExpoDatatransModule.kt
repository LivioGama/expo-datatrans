package expo.modules.datatrans

import androidx.lifecycle.LifecycleOwner
import ch.datatrans.payment.api.*
import ch.datatrans.payment.paymentmethods.*
import expo.modules.kotlin.Promise
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import android.util.Log

class ExpoDatatransModule : Module() {
    private var currentPromise: Promise? = null

    override fun definition() = ModuleDefinition {
        Name("ExpoDatatrans")
        AsyncFunction("transaction") { mobileToken: String, options: Map<String, Any>?, promise: Promise ->
            handleTransaction(mobileToken, options ?: emptyMap(), promise)
        }
    }

    private fun handleTransaction(mobileToken: String, options: Map<String, Any>, promise: Promise) {
        currentPromise = promise

        val activity = appContext.currentActivity ?: run {
            promise.reject("DATATRANS_ERROR", "Activity doesn't exist", null)
            return
        }

        try {
            val transaction = createTransaction(mobileToken, options)

            if (activity !is LifecycleOwner) {
                throw IllegalStateException("Activity must implement LifecycleOwner")
            }

            activity.runOnUiThread {
                transaction.listener = createTransactionListener()
                TransactionRegistry.startTransaction(activity, transaction)
            }
        } catch (e: Exception) {
            Log.e("error", e.message ?: "Unknown error", e)
            promise.reject("DATATRANS_ERROR", e.message, e)
        }
    }

    private fun createTransactionListener() = object : TransactionListener {
        override fun onTransactionCancel(mobileToken: String) {
            resolveTransaction(TransactionResponse("cancel"))
        }

        override fun onTransactionError(exception: ch.datatrans.payment.exception.TransactionException) {
            resolveTransaction(TransactionResponse(
                status = "error",
                data = mapOf(
                    "transactionId" to (exception.transactionId.orEmpty()),
                    "message" to (exception.message ?: "Unknown error"),
                    "paymentMethodType" to PaymentMethodConverter.fromNative(exception.paymentMethodType)
                )
            ))
        }

        override fun onTransactionSuccess(result: TransactionSuccess) {
            val data = mutableMapOf<String, Any>(
                "transactionId" to result.transactionId,
                "paymentMethodType" to PaymentMethodConverter.fromNative(result.paymentMethodType)
            )

            result.savedPaymentMethod?.let { savedPaymentMethod ->
                data["savedPaymentMethod"] = mapOf(
                    "type" to PaymentMethodConverter.fromNative(savedPaymentMethod.type),
                    "alias" to savedPaymentMethod.alias
                )
            }

            resolveTransaction(TransactionResponse(
                status = "success",
                data = data
            ))
        }
    }

    private fun createTransaction(mobileToken: String, options: Map<String, Any>): Transaction =
        parseSavedCards(options).let { savedCards ->
            (if (savedCards.isNotEmpty()) Transaction(mobileToken, savedCards)
             else Transaction(mobileToken))
                .apply { this.options = createTransactionOptions(options) }
        }

    private fun createTransactionOptions(options: Map<String, Any>) = TransactionOptions().apply {
        appCallbackScheme = options["appCallbackScheme"] as? String ?: "defaultScheme"
        isTesting = options["isTesting"] as? Boolean ?: false
        useCertificatePinning = options["isUseCertificatePinning"] as? Boolean ?: false

        (options["googlePayConfig"] as? Map<String, Any>)?.also { googlePayConfig ->
            val merchantId = googlePayConfig["merchantId"] as String
            val supportedNetworks = (googlePayConfig["supportedNetworks"] as List<String>).mapNotNull {
                PaymentMethodConverter.fromString(it)?.toNativeType()
            }
            this.googlePayConfig = GooglePayConfig.Builder(supportedNetworks, merchantId).build()
        }

        (options["samsungPayConfig"] as? Map<String, Any>)?.also { samsungPayConfig ->
            val merchantId = samsungPayConfig["merchantId"] as String
            val supportedNetworks = (samsungPayConfig["supportedNetworks"] as List<String>).mapNotNull {
                PaymentMethodConverter.fromString(it)?.toNativeType()
            }
            this.samsungPayConfig = SamsungPayConfig(supportedNetworks, merchantId)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun parseSavedCards(options: Map<String, Any>): List<SavedCard> =
        (options["aliasPaymentMethods"] as? List<Map<String, Any>>)?.mapNotNull { apm ->
            PaymentMethodConverter.fromString(apm["paymentMethod"] as String)?.toNativeType()?.let { paymentMethodType ->
                SavedCard(
                    paymentMethodType,
                    apm["alias"] as String,
                    CardExpiryDate(
                        (apm["expiryMonth"] as Number).toInt(),
                        (apm["expiryYear"] as Number).toInt()
                    ),
                    apm["ccNumber"] as String,
                    ""
                )
            }
        } ?: emptyList()

    private fun resolveTransaction(response: TransactionResponse) {
        currentPromise?.resolve(mapOf(
            "status" to response.status,
            "data" to response.data
        ))
    }

    private data class TransactionResponse(
        val status: String,
        val data: Map<String, Any> = emptyMap()
    )
}
