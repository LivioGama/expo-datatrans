import ExpoDatatrans, {PaymentMethodType} from 'expo-datatrans'
import {Button, StyleSheet, View} from 'react-native'

export default function Index() {
  // console.log(ExpoDatatrans)

  const handleTransaction = async () => {
    try {
      const options = {
        aliasPaymentMethods: [
          {
            alias: '...',
            ccNumber: '...',
            expiryMonth: 12,
            expiryYear: 2024,
            paymentMethod: PaymentMethodType.VISA,
          },
        ],
        isTesting: true,
        isUseCertificatePinning: false,
        appCallbackScheme: 'myapp',
        googlePayConfig: {
          merchantId: 'YOUR_GOOGLE_MERCHANT_ID',
          supportedNetworks: [PaymentMethodType.MASTERCARD, PaymentMethodType.VISA],
        },
        samsungPayConfig: {
          merchantId: 'YOUR_SAMSUNG_MERCHANT_ID',
          supportedNetworks: [PaymentMethodType.MASTERCARD, PaymentMethodType.VISA],
        },
        applePayConfig: {
          merchantId: 'YOUR_APPLE_MERCHANT_ID',
          supportedNetworks: [PaymentMethodType.MASTERCARD, PaymentMethodType.VISA],
        },
      }

      const result = await ExpoDatatrans.transaction('mobileToken123', options)

      if (result.status === 'success') {
        console.log('success', result)
      }
    } catch (error) {
      console.error(error)
    }
  }
  return (
    <View style={styles.container}>
      <Button title='Open datatrans' onPress={handleTransaction} />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
})
