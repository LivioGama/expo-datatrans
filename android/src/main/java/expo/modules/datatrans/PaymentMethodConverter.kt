package expo.modules.datatrans

import ch.datatrans.payment.paymentmethods.PaymentMethodType

enum class PaymentMethodConverter(val identifier: String) {
    VISA("VISA"),
    MASTERCARD("MASTERCARD"),
    DINERS_CLUB("DINERS_CLUB"),
    AMERICAN_EXPRESS("AMERICAN_EXPRESS"),
    JCB("JCB"),
    UATP("UATP"),
    DISCOVER("DISCOVER"),
    SUPERCARD("SUPERCARD"),
    POST_FINANCE_CARD("POST_FINANCE_CARD"),
    POST_FINANCE_EFINANCE("POST_FINANCE_EFINANCE"),
    PAYPAL("PAYPAL"),
    EASYPAY("EASYPAY"),
    SEPA("SEPA"),
    SWISS_BILLING("SWISS_BILLING"),
    TWINT("TWINT"),
    REKA("REKA"),
    CEMBRA_PAY("CEMBRA_PAY"),
    SWISS_PASS("SWISS_PASS"),
    POWERPAY("POWERPAY"),
    PAYCARD("PAYCARD"),
    PAYSAFECARD("PAYSAFECARD"),
    BONCARD("BONCARD"),
    ELO("ELO"),
    HIPERCARD("HIPERCARD"),
    KLARNA("KLARNA"),
    DANKORT("DANKORT"),
    SWISH("SWISH"),
    VIPPS("VIPPS"),
    MOBILE_PAY("MOBILE_PAY"),
    MAESTRO("MAESTRO"),
    UNION_PAY("UNION_PAY"),
    HALF_FARE_PLUS("HALF_FARE_PLUS"),
    GOOGLE_PAY("GOOGLE_PAY"),
    SAMSUNG_PAY("SAMSUNG_PAY");

    fun toNativeType(): PaymentMethodType? {
        return when (this) {
            VISA -> PaymentMethodType.VISA
            MASTERCARD -> PaymentMethodType.MASTER_CARD
            DINERS_CLUB -> PaymentMethodType.DINERS_CLUB
            AMERICAN_EXPRESS -> PaymentMethodType.AMERICAN_EXPRESS
            JCB -> PaymentMethodType.JCB
            UATP -> PaymentMethodType.UATP
            DISCOVER -> PaymentMethodType.DISCOVER
            SUPERCARD -> PaymentMethodType.SUPERCARD
            POST_FINANCE_CARD -> PaymentMethodType.POST_FINANCE_CARD
            POST_FINANCE_EFINANCE -> PaymentMethodType.POST_FINANCE_EFINANCE
            PAYPAL -> PaymentMethodType.PAY_PAL
            EASYPAY -> PaymentMethodType.EASYPAY
            SEPA -> PaymentMethodType.SEPA
            SWISS_BILLING -> PaymentMethodType.SWISS_BILLING
            TWINT -> PaymentMethodType.TWINT
            REKA -> PaymentMethodType.REKA
            CEMBRA_PAY -> PaymentMethodType.CEMBRA_PAY
            SWISS_PASS -> PaymentMethodType.SWISS_PASS
            POWERPAY -> PaymentMethodType.POWERPAY
            PAYCARD -> PaymentMethodType.PAYCARD
            PAYSAFECARD -> PaymentMethodType.PAYSAFECARD
            BONCARD -> PaymentMethodType.BONCARD
            ELO -> PaymentMethodType.ELO_CARD
            HIPERCARD -> PaymentMethodType.HIPERCARD
            KLARNA -> PaymentMethodType.KLARNA
            DANKORT -> PaymentMethodType.DANKORT
            SWISH -> PaymentMethodType.SWISH
            VIPPS -> PaymentMethodType.VIPPS
            MOBILE_PAY -> PaymentMethodType.MOBILE_PAY
            MAESTRO -> PaymentMethodType.MAESTRO
            UNION_PAY -> PaymentMethodType.UNION_PAY
            HALF_FARE_PLUS -> PaymentMethodType.HALF_FARE_PLUS
            GOOGLE_PAY -> PaymentMethodType.GOOGLE_PAY
            SAMSUNG_PAY -> PaymentMethodType.SAMSUNG_PAY
        }
    }

    companion object {
        fun fromNative(paymentMethodType: PaymentMethodType?): String {
            return when (paymentMethodType) {
                PaymentMethodType.VISA -> VISA.identifier
                PaymentMethodType.MASTER_CARD -> MASTERCARD.identifier
                PaymentMethodType.DINERS_CLUB -> DINERS_CLUB.identifier
                PaymentMethodType.AMERICAN_EXPRESS -> AMERICAN_EXPRESS.identifier
                PaymentMethodType.JCB -> JCB.identifier
                PaymentMethodType.UATP -> UATP.identifier
                PaymentMethodType.DISCOVER -> DISCOVER.identifier
                PaymentMethodType.SUPERCARD -> SUPERCARD.identifier
                PaymentMethodType.POST_FINANCE_CARD -> POST_FINANCE_CARD.identifier
                PaymentMethodType.POST_FINANCE_EFINANCE -> POST_FINANCE_EFINANCE.identifier
                PaymentMethodType.PAY_PAL -> PAYPAL.identifier
                PaymentMethodType.EASYPAY -> EASYPAY.identifier
                PaymentMethodType.SEPA -> SEPA.identifier
                PaymentMethodType.SWISS_BILLING -> SWISS_BILLING.identifier
                PaymentMethodType.TWINT -> TWINT.identifier
                PaymentMethodType.REKA -> REKA.identifier
                PaymentMethodType.CEMBRA_PAY -> CEMBRA_PAY.identifier
                PaymentMethodType.SWISS_PASS -> SWISS_PASS.identifier
                PaymentMethodType.POWERPAY -> POWERPAY.identifier
                PaymentMethodType.PAYCARD -> PAYCARD.identifier
                PaymentMethodType.PAYSAFECARD -> PAYSAFECARD.identifier
                PaymentMethodType.BONCARD -> BONCARD.identifier
                PaymentMethodType.ELO_CARD -> ELO.identifier
                PaymentMethodType.HIPERCARD -> HIPERCARD.identifier
                PaymentMethodType.KLARNA -> KLARNA.identifier
                PaymentMethodType.DANKORT -> DANKORT.identifier
                PaymentMethodType.SWISH -> SWISH.identifier
                PaymentMethodType.VIPPS -> VIPPS.identifier
                PaymentMethodType.MOBILE_PAY -> MOBILE_PAY.identifier
                PaymentMethodType.MAESTRO -> MAESTRO.identifier
                PaymentMethodType.UNION_PAY -> UNION_PAY.identifier
                PaymentMethodType.HALF_FARE_PLUS -> HALF_FARE_PLUS.identifier
                PaymentMethodType.GOOGLE_PAY -> GOOGLE_PAY.identifier
                PaymentMethodType.SAMSUNG_PAY -> SAMSUNG_PAY.identifier
                else -> "UNKNOWN"
            }
        }

        fun fromString(value: String): PaymentMethodConverter? {
            return values().find { it.identifier == value }
        }
    }
}
