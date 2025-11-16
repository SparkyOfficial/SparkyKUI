# Kotlin Multiplatform App

Кроссплатформенное приложение на Kotlin Multiplatform с Compose Multiplatform UI.

## Поддерживаемые платформы

- **Android** (API 24+)
- **Desktop** (Windows, macOS, Linux)
- **iOS** (iOS 14+)

## Технологический стек

- Kotlin Multiplatform
- Compose Multiplatform
- Material Design 3
- Voyager (навигация)
- Koin (dependency injection)
- Kotlin Coroutines

## Структура проекта

```
kotlin-multiplatform-app/
├── composeApp/              # Основной модуль приложения
│   ├── src/
│   │   ├── commonMain/      # Общий код для всех платформ
│   │   ├── androidMain/     # Android-специфичный код
│   │   ├── desktopMain/     # Desktop-специфичный код
│   │   └── iosMain/         # iOS-специфичный код
│   └── build.gradle.kts
├── gradle/                  # Gradle конфигурация
├── build.gradle.kts         # Корневой build файл
└── settings.gradle.kts      # Настройки проекта
```

## Требования

- JDK 11 или выше
- Android Studio Hedgehog (2023.1.1) или новее
- Для iOS: Xcode 15+ (только на macOS)

## Запуск приложения

### Android
```bash
./gradlew :composeApp:installDebug
```

### Desktop
```bash
./gradlew :composeApp:run
```

### iOS
Откройте проект в Xcode и запустите на симуляторе или устройстве.

## Сборка

### Android APK
```bash
./gradlew :composeApp:assembleDebug
```

### Desktop приложение
```bash
./gradlew :composeApp:packageDistributionForCurrentOS
```

## Разработка

Проект следует архитектуре Clean Architecture с разделением на слои:
- **Presentation**: UI компоненты и ViewModels
- **Domain**: Бизнес-логика
- **Data**: Репозитории и источники данных

## Лицензия

MIT License
