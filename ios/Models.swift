import Datatrans

struct TransactionResponse {
    enum ResponseStatus: String {
        case success, error, cancel
    }

    let status: ResponseStatus
    let data: [String: Any]

    var dictionary: [String: Any] {
        ["status": status.rawValue,
         "data": data]
    }
}

struct AliasPaymentMethod {
    let alias: String
    let ccNumber: String
    let expiryMonth: Int
    let expiryYear: Int
}

enum PaymentMethodConverter: String {
    case visa = "VISA"
    case mastercard = "MASTERCARD"
    case dinersClub = "DINERS_CLUB"
    case americanExpress = "AMERICAN_EXPRESS"
    case jcb = "JCB"
    case uatp = "UATP"
    case discover = "DISCOVER"
    case supercard = "SUPERCARD"
    case postFinanceCard = "POST_FINANCE_CARD"
    case postFinanceEFinance = "POST_FINANCE_EFINANCE"
    case payPal = "PAYPAL"
    case easypay = "EASYPAY"
    case sepa = "SEPA"
    case swissBilling = "SWISS_BILLING"
    case twint = "TWINT"
    case applePay = "APPLE_PAY"
    case reka = "REKA"
    case cembraPay = "CEMBRA_PAY"
    case swissPass = "SWISS_PASS"
    case powerpay = "POWERPAY"
    case paycard = "PAYCARD"
    case paysafecard = "PAYSAFECARD"
    case boncard = "BONCARD"
    case elo = "ELO"
    case hipercard = "HIPERCARD"
    case klarna = "KLARNA"
    case dankort = "DANKORT"
    case swish = "SWISH"
    case vipps = "VIPPS"
    case mobilePay = "MOBILE_PAY"
    case maestro = "MAESTRO"
    case chinaUnionPay = "UNION_PAY"
    case halfFarePlus = "HALF_FARE_PLUS"

    var nativeType: PaymentMethodType? {
        switch self {
        case .visa: return .Visa
        case .mastercard: return .MasterCard
        case .dinersClub: return .DinersClub
        case .americanExpress: return .AmericanExpress
        case .jcb: return .JCB
        case .uatp: return .UATP
        case .discover: return .Discover
        case .supercard: return .Supercard
        case .postFinanceCard: return .PostFinanceCard
        case .postFinanceEFinance: return .PostFinanceEFinance
        case .payPal: return .PayPal
        case .easypay: return .Easypay
        case .sepa: return .SEPA
        case .swissBilling: return .SwissBilling
        case .twint: return .Twint
        case .applePay: return .ApplePay
        case .reka: return .Reka
        case .cembraPay: return .CembraPay
        case .swissPass: return .SwissPass
        case .powerpay: return .Powerpay
        case .paycard: return .Paycard
        case .paysafecard: return .Paysafecard
        case .boncard: return .Boncard
        case .elo: return .Elo
        case .hipercard: return .Hipercard
        case .klarna: return .Klarna
        case .dankort: return .Dankort
        case .swish: return .Swish
        case .vipps: return .Vipps
        case .mobilePay: return .MobilePay
        case .maestro: return .Maestro
        case .chinaUnionPay: return .ChinaUnionPay
        case .halfFarePlus: return .HalfFarePlus
        }
    }
}

func convertFromNative(_ paymentMethod: PaymentMethodType?) -> String {
    guard let method = paymentMethod else { return "UNKNOWN" }
    switch method {
    case .Visa: return PaymentMethodConverter.visa.rawValue
    case .MasterCard: return PaymentMethodConverter.mastercard.rawValue
    case .DinersClub: return PaymentMethodConverter.dinersClub.rawValue
    case .AmericanExpress: return PaymentMethodConverter.americanExpress.rawValue
    case .JCB: return PaymentMethodConverter.jcb.rawValue
    case .UATP: return PaymentMethodConverter.uatp.rawValue
    case .Discover: return PaymentMethodConverter.discover.rawValue
    case .Supercard: return PaymentMethodConverter.supercard.rawValue
    case .PostFinanceCard: return PaymentMethodConverter.postFinanceCard.rawValue
    case .PostFinanceEFinance: return PaymentMethodConverter.postFinanceEFinance.rawValue
    case .PayPal: return PaymentMethodConverter.payPal.rawValue
    case .Easypay: return PaymentMethodConverter.easypay.rawValue
    case .SEPA: return PaymentMethodConverter.sepa.rawValue
    case .SwissBilling: return PaymentMethodConverter.swissBilling.rawValue
    case .Twint: return PaymentMethodConverter.twint.rawValue
    case .Reka: return PaymentMethodConverter.reka.rawValue
    case .ApplePay: return PaymentMethodConverter.applePay.rawValue
    case .CembraPay: return PaymentMethodConverter.cembraPay.rawValue
    case .SwissPass: return PaymentMethodConverter.swissPass.rawValue
    case .Powerpay: return PaymentMethodConverter.powerpay.rawValue
    case .Paycard: return PaymentMethodConverter.paycard.rawValue
    case .Paysafecard: return PaymentMethodConverter.paysafecard.rawValue
    case .Boncard: return PaymentMethodConverter.boncard.rawValue
    case .Elo: return PaymentMethodConverter.elo.rawValue
    case .Hipercard: return PaymentMethodConverter.hipercard.rawValue
    case .Klarna: return PaymentMethodConverter.klarna.rawValue
    case .Dankort: return PaymentMethodConverter.dankort.rawValue
    case .Swish: return PaymentMethodConverter.swish.rawValue
    case .Vipps: return PaymentMethodConverter.vipps.rawValue
    case .MobilePay: return PaymentMethodConverter.mobilePay.rawValue
    case .Maestro: return PaymentMethodConverter.maestro.rawValue
    case .ChinaUnionPay: return PaymentMethodConverter.chinaUnionPay.rawValue
    case .HalfFarePlus: return PaymentMethodConverter.halfFarePlus.rawValue
    default: return "UNKNOWN"
    }
}
