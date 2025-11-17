package com.example.kmpapp.domain.error

/**
 * Sealed class для представления различных типов ошибок в приложении
 * Используется для типобезопасной обработки ошибок на всех уровнях приложения
 */
sealed class AppError {
    /**
     * Ошибка сети (отсутствие соединения, таймаут и т.д.)
     * @param message Описание ошибки
     */
    data class NetworkError(val message: String) : AppError()
    
    /**
     * Ошибка валидации данных (некорректный ввод пользователя)
     * @param field Поле, в котором произошла ошибка
     * @param message Описание ошибки валидации
     */
    data class ValidationError(val field: String, val message: String) : AppError()
    
    /**
     * Неизвестная ошибка (непредвиденное исключение)
     * @param throwable Исходное исключение
     */
    data class UnknownError(val throwable: Throwable) : AppError()
    
    /**
     * Преобразование ошибки в понятное пользователю сообщение
     * @return Текст сообщения об ошибке для отображения пользователю
     */
    fun toUserMessage(): String {
        return when (this) {
            is NetworkError -> "Ошибка сети: $message"
            is ValidationError -> "Ошибка валидации в поле '$field': $message"
            is UnknownError -> "Произошла неизвестная ошибка: ${throwable.message ?: "Неизвестная причина"}"
        }
    }
}
