import * as React from 'react';

import { ExpoDatatransViewProps } from './ExpoDatatrans.types';

export default function ExpoDatatransView(props: ExpoDatatransViewProps) {
  return (
    <div>
      <iframe
        style={{ flex: 1 }}
        src={props.url}
        onLoad={() => props.onLoad({ nativeEvent: { url: props.url } })}
      />
    </div>
  );
}
