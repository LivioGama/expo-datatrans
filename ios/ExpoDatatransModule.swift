import Datatrans
import ExpoModulesCore

public class ExpoDatatransModule: Module {
    private var currentPromise: Promise?

    public func definition() -> ModuleDefinition {
        Name("ExpoDatatrans")

        AsyncFunction("transaction") { [weak self] (mobileToken: String, options: [String: Any]?, promise: Promise) in
            self?.handleTransaction(mobileToken: mobileToken, options: options ?? [:], promise: promise)
        }
    }

    private func handleTransaction(mobileToken: String, options: [String: Any]?, promise: Promise) {
        currentPromise = promise

        DispatchQueue.main.async {
            guard let presentingController = self.getPresentingController() else {
                promise.reject("DATATRANS_ERROR", "ViewController doesn't exist")
                return
            }

            let transaction = self.createTransaction(mobileToken: mobileToken, options: options)
            transaction.delegate = self
            transaction.start(presentingController: presentingController)
        }
    }

    private func getPresentingController() -> UIViewController? {
        guard let scene = UIApplication.shared.connectedScenes.first(where: { $0.activationState == .foregroundActive }) as? UIWindowScene,
              let window = scene.windows.first(where: { $0.isKeyWindow })
        else {
            return nil
        }
        return window.rootViewController
    }

    private func createTransaction(mobileToken: String, options: [String: Any]?) -> Transaction {
        let savedCards = parseSavedCards(from: options)
        let transaction = Transaction(mobileToken: mobileToken, savedPaymentMethods: savedCards)
        let transactionOptions = TransactionOptions()
        transactionOptions.appCallbackScheme = options?["appCallbackScheme"] as? String ?? "defaultScheme"
        transactionOptions.testing = options?["isTesting"] as? Bool ?? false
        transactionOptions.useCertificatePinning = options?["isUseCertificatePinning"] as? Bool ?? false

        if let applePayConfig = options?["applePayConfig"] as? [String: Any],
           let merchantId = applePayConfig["merchantId"] as? String,
           let supportedNetworks = applePayConfig["supportedNetworks"] as? [String] {
            transactionOptions.applePayConfig = ApplePayConfig(
                applePayMerchantId: merchantId,
                supportedNetworks: supportedNetworks.toApplePayNetworks()
            )
        }
        transaction.options = transactionOptions
        return transaction
    }

    private func parseSavedCards(from options: [String: Any]?) -> [SavedCard] {
        guard let options = options else { return [] }
        let aliasPaymentMethods = options["aliasPaymentMethods"] as? [[String: Any]] ?? []

        return aliasPaymentMethods.compactMap { (apm: [String: Any]) -> SavedCard? in
            guard let paymentMethod = options["paymentMethod"] as? String,
                  let paymentMethodType = PaymentMethodConverter(rawValue: paymentMethod)?.nativeType,
                  let alias = apm["alias"] as? String,
                  let ccNumber = apm["ccNumber"] as? String,
                  let expiryMonth = apm["expiryMonth"] as? Int,
                  let expiryYear = apm["expiryYear"] as? Int
            else {
                return nil
            }

            let expiryDate = CardExpiryDate(month: expiryMonth, year: expiryYear)
            return SavedCard(type: paymentMethodType,
                             alias: alias,
                             cardExpiryDate: expiryDate,
                             maskedCardNumber: ccNumber,
                             cardholder: "")
        }
    }
}

extension ExpoDatatransModule: TransactionDelegate {
    public func transactionDidCancel(_ transaction: Transaction) {
        resolveTransaction(.init(status: .cancel,
                                 data: [:]))
    }

    public func transactionDidFail(_ transaction: Transaction, error: TransactionError) {
        resolveTransaction(.init(status: .error,
                                 data: ["transactionId": error.transactionId ?? "",
                                        "message": error.localizedDescription,
                                        "paymentMethodType": convertFromNative(error.paymentMethodType)]))
    }

    public func transactionDidFinish(_ transaction: Transaction, result: TransactionSuccess) {
        var data: [String: Any] = [
            "transactionId": result.transactionId,
            "paymentMethodType": convertFromNative(result.paymentMethodType)
        ]

        if let savedPaymentMethod = result.savedPaymentMethod {
            data["savedPaymentMethod"] = [
                "type": convertFromNative(savedPaymentMethod.type),
                "alias": savedPaymentMethod.alias
            ]
        }

        resolveTransaction(.init(status: .success, data: data))
    }

    private func resolveTransaction(_ response: TransactionResponse) {
        currentPromise?.resolve(response.dictionary)
    }
}
