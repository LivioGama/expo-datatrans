import { requireNativeView } from 'expo';
import * as React from 'react';

import { ExpoDatatransViewProps } from './ExpoDatatrans.types';

const NativeView: React.ComponentType<ExpoDatatransViewProps> =
  requireNativeView('ExpoDatatrans');

export default function ExpoDatatransView(props: ExpoDatatransViewProps) {
  return <NativeView {...props} />;
}
