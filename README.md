# 📦 expo-datatrans

An Expo module for integrating Datatrans Payment Service Provider (PSP) into your Expo applications.

## 🚀 Overview

`expo-datatrans` provides a seamless native bridge to implement Datatrans payment solutions in Expo-powered mobile
applications.

Datatrans is one of the leading Swiss Payment Service Provider (PSP), offering secure and reliable payment processing services.

## 🛠 Installation

Install the module using Expo CLI:

```bash
bunx expo install expo-datatrans
```

### Caveat

To run the Android example, you need to run:

```bash
cd example
node scripts/update-gradle.js
```

This is because we need a specific repository to add the Datatrans SDK to the project. 

You will have to do the same on your own project, so I recommend copying the example project scripts to prebuild clean and ensure the repository is added to the `build.gradle` file after removing and regenerating the Android project.


## ✨ Features

- Seamless integration with the Datatrans payment gateway
- Native implementation for iOS and Android
- TypeScript support with included type definitions
- Built specifically for the Expo ecosystem

## 📖 Usage

Import the module into your JavaScript/TypeScript code:

```typescript
import ExpoDatatrans from 'expo-datatrans';
```

Example: Processing a Payment

```jsx
import ExpoDatatrans, {Options, PaymentMethodType, TransactionResult} from 'expo-datatrans';
import {Button, View} from 'react-native';

export default function Index() {
    const handleTransaction = async () => {
        try {
            const options: Options = {
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
            };

            const result: TransactionResult = await ExpoDatatrans.transaction('mobileToken123', options);

            if (result.type === 'success') {
                console.log('✅ Transaction Success:', result);
            }
        } catch (error) {
            console.error('❌ Transaction Error:', error);
        }
    };

    return (
        <View>
            <Button title="Start Payment" onPress={handleTransaction} />
        </View>
    )
}
```

## 🧑‍💻 Technical Details
- Written in TypeScript to provide a smooth development experience with full type safety.
- Includes robust support for both iOS and Android platforms.
- Supports alias-based payment methods and additional customization options.

Type Definitions

The following key types are included for ease of integration:
- [Options](https://github.com/LivioGama/expo-datatrans/blob/main/src/ExpoDatatrans.types.ts)
- [PaymentMethodType]()
- [TransactionResult](https://github.com/LivioGama/expo-datatrans/blob/main/src/ExpoDatatrans.types.ts)

## 📄 License

This project is licensed under the MIT License.

## ❤️ Contributing

Contributions are welcome! Feel free to submit issues, feature requests, or pull requests on the GitHub repository.
