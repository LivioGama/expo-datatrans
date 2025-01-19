enum PaymentMethodType {
  VISA = 'VISA',
  MASTERCARD = 'MASTERCARD',
  DINERS_CLUB = 'DINERS_CLUB',
  AMERICAN_EXPRESS = 'AMERICAN_EXPRESS',
  JCB = 'JCB',
  UATP = 'UATP',
  DISCOVER = 'DISCOVER',
  SUPERCARD = 'SUPERCARD',
  POST_FINANCE_CARD = 'POST_FINANCE_CARD',
  POST_FINANCE_EFINANCE = 'POST_FINANCE_EFINANCE',
  PAYPAL = 'PAYPAL',
  EASYPAY = 'EASYPAY',
  SEPA = 'SEPA',
  SWISS_BILLING = 'SWISS_BILLING',
  TWINT = 'TWINT',
  REKA = 'REKA',
  CEMBRA_PAY = 'CEMBRA_PAY',
  SWISS_PASS = 'SWISS_PASS',
  POWERPAY = 'POWERPAY',
  PAYCARD = 'PAYCARD',
  PAYSAFECARD = 'PAYSAFECARD',
  BONCARD = 'BONCARD',
  ELO = 'ELO',
  HIPERCARD = 'HIPERCARD',
  KLARNA = 'KLARNA',
  DANKORT = 'DANKORT',
  SWISH = 'SWISH',
  VIPPS = 'VIPPS',
  MOBILE_PAY = 'MOBILE_PAY',
  MAESTRO = 'MAESTRO',
  UNION_PAY = 'UNION_PAY',
  HALF_FARE_PLUS = 'HALF_FARE_PLUS',
  GOOGLE_PAY = 'GOOGLE_PAY',
  SAMSUNG_PAY = 'SAMSUNG_PAY',
  APPLE_PAY = 'APPLE_PAY',
}

type AliasPaymentMethod = {
  alias?: string
  ccNumber?: string
  expiryMonth?: number
  expiryYear?: number
  paymentMethod?: PaymentMethodType
}

type Options = {
  aliasPaymentMethods?: AliasPaymentMethod[]
  isTesting?: boolean
  isUseCertificatePinning?: boolean
  appCallbackScheme?: string
  googlePayConfig?: {
    merchantId: string
    supportedNetworks: PaymentMethodType[]
  }
  samsungPayConfig?: {
    merchantId: string
    supportedNetworks: PaymentMethodType[]
  }
  applePayConfig?: {
    merchantId: string
    supportedNetworks: PaymentMethodType[]
  }
}

type TransactionResult = {
  status: 'success' | 'error' | 'cancel'
  data: {
    transactionId?: string
    savedPaymentMethod?: {
      type: PaymentMethodType
      alias: string
    }
    paymentMethodType?: PaymentMethodType
    message?: string
  }
}

export {PaymentMethodType}
export type {AliasPaymentMethod, Options, TransactionResult}
