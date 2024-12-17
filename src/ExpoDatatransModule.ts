import {requireNativeModule} from 'expo-modules-core'
import {Options, TransactionResult} from './ExpoDatatrans.types'

interface ExpoDatatransModule {
  transaction(mobileToken: string, options?: Options): Promise<TransactionResult>
}

export default requireNativeModule<ExpoDatatransModule>('ExpoDatatrans')
