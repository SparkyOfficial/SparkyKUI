# Kotlin Multiplatform App

Шаблонное Современное кроссплатформенное приложение на Kotlin Multiplatform с Compose Multiplatform UI, демонстрирующее адаптивную верстку, плавные анимации и единую кодовую базу для всех платформ.

## ✨ Особенности

- 🎨 **Material Design 3** - современный UI с поддержкой светлой и темной темы
- 📱 **Адаптивная верстка** - автоматическая адаптация под размер экрана (мобильные, планшеты, десктоп)
- 🎭 **Плавные анимации** - переходы между экранами, hover эффекты, анимации компонентов
- 🚀 **Высокая производительность** - оптимизированный код с минимальной recomposition
- 🔄 **Единая кодовая база** - 95%+ общего кода для всех платформ
- 🧩 **Модульная архитектура** - Clean Architecture с разделением на слои

## Поддерживаемые платформы

- **Android** (API 24+) - телефоны и планшеты
- **Desktop** (Windows, macOS, Linux) - нативные приложения
- **iOS** (iOS 14+) - iPhone и iPad

## Технологический стек

- **Kotlin Multiplatform** - общая бизнес-логика
- **Compose Multiplatform** - декларативный UI фреймворк
- **Material Design 3** - современная дизайн-система
- **Voyager** - типобезопасная навигация
- **Koin** - dependency injection
- **Kotlin Coroutines** - асинхронные операции


```

## Требования

- JDK 11 или выше
- Android Studio Hedgehog (2023.1.1) или новее
- Для iOS: Xcode 15+ (только на macOS)

## 🚀 Быстрый старт

### Android
```bash
# Установка на устройство/эмулятор
.\gradlew.bat installDebug

# Или откройте проект в Android Studio и нажмите Run
```

### Desktop
```bash
# Запуск приложения
.\gradlew.bat runDistributable

# Создание дистрибутива
.\gradlew.bat packageDistributionForCurrentOS
```

### iOS
```bash
# Откройте iosApp/iosApp.xcworkspace в Xcode
# Выберите симулятор или устройство и нажмите Run (Cmd+R)
```

Подробные инструкции по запуску см. в [PLATFORM_RUN_GUIDE.md](PLATFORM_RUN_GUIDE.md)



## 🎨 Адаптивная верстка

Приложение автоматически адаптируется под размер экрана:

- **COMPACT** (< 600dp): Мобильная компоновка с нижней навигацией
- **MEDIUM** (600-840dp): Планшетная компоновка
- **EXPANDED** (> 840dp): Десктопная компоновка с боковой навигацией

## ⚡ Производительность

- Загрузка приложения: < 3 секунд
- Переходы между экранами: < 300ms
- Визуальная обратная связь: < 100ms
- Целевой FPS: 60

## 🧪 Тестирование

```bash
# Запуск всех тестов
.\gradlew.bat test

# Тесты для конкретной платформы
.\gradlew.bat testDebugUnitTest        # Android
.\gradlew.bat desktopTest              # Desktop
.\gradlew.bat iosSimulatorArm64Test    # iOS

# UI тесты (Android)
.\gradlew.bat connectedAndroidTest
```

## 🏗️ Архитектура

Проект следует принципам Clean Architecture:

```
Presentation Layer (UI)
    ↓
Domain Layer (Business Logic)
    ↓
Data Layer (Repositories)
```

### Слои:
- **Presentation**: Compose UI, ViewModels, Navigation
- **Domain**: Use Cases, Domain Models, Repository Interfaces
- **Data**: Repository Implementations, Data Sources

### Ключевые паттерны:
- MVVM (Model-View-ViewModel)
- Repository Pattern
- Dependency Injection (Koin)
- Unidirectional Data Flow
## 🤝 Вклад в проект

Проект создан как демонстрация возможностей Kotlin Multiplatform и Compose Multiplatform.

## 📄 Лицензия

MIT License

## 🔗 Полезные ссылки

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Material Design 3](https://m3.material.io/)
- [Voyager Navigation](https://voyager.adriel.cafe/)
- [Koin DI](https://insert-koin.io/)

---

**Статус проекта**: ✅ Готов к использованию
**Последнее обновление**: 17 ноября 2025
