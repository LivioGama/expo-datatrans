import { NativeModule, requireNativeModule } from 'expo';

import { ExpoDatatransModuleEvents } from './ExpoDatatrans.types';

declare class ExpoDatatransModule extends NativeModule<ExpoDatatransModuleEvents> {
  PI: number;
  hello(): string;
  setValueAsync(value: string): Promise<void>;
}

// This call loads the native module object from the JSI.
export default requireNativeModule<ExpoDatatransModule>('ExpoDatatrans');
