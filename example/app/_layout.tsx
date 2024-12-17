import {DarkTheme, ThemeProvider} from '@react-navigation/native'
import {Stack} from 'expo-router'
import * as SplashScreen from 'expo-splash-screen'
import {useEffect} from 'react'
import 'react-native-reanimated'

SplashScreen.preventAutoHideAsync()

export default function RootLayout() {
  useEffect(() => {
    setTimeout(() => SplashScreen.hideAsync(), 1000)
  }, [])

  return <RootLayoutNav />
}

function RootLayoutNav() {
  return (
    <ThemeProvider value={DarkTheme}>
      <Stack>
        <Stack.Screen name='index' options={{headerTitle: 'Datatrans example'}} />
      </Stack>
    </ThemeProvider>
  )
}
