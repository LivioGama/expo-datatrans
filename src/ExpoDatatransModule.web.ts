import { registerWebModule, NativeModule } from 'expo';

import { ExpoDatatransModuleEvents } from './ExpoDatatrans.types';

class ExpoDatatransModule extends NativeModule<ExpoDatatransModuleEvents> {
  PI = Math.PI;
  async setValueAsync(value: string): Promise<void> {
    this.emit('onChange', { value });
  }
  hello() {
    return 'Hello world! 👋';
  }
}

export default registerWebModule(ExpoDatatransModule);
