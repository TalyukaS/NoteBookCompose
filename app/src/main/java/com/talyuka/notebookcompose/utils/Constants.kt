package com.talyuka.notebookcompose.utils

import com.talyuka.notebookcompose.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
lateinit var REPOSITORY: DatabaseRepository
lateinit var LOGIN: String
lateinit var PASSWORD: String

object Constants {
    object Keys {
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTES = "Добавить запись"
        const val ADD_NOTES = "Добавить"
        const val TITLE_NEW_NOTES = "Заголовок"
        const val SUBTITLE_NEW_NOTES = "Описание"
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
        const val NAME_DATABASE = "Какую базу использовать?"
        const val ROOM_DATABASE = "Локальная"
        const val FIREBASE_DATABASE = "Удаленная"
        const val NAME_APP = "Блокнот"
        const val ID = "Id"
        const val NONE = "none"
        const val UPDATE = "Обновить"
        const val DELETE = "Удалить"
        const val NAV_BACK = "Возврат"
        const val EDIT_NOTE = "Редактировать"
        const val UPDATE_NOTE = "Изменить"
        const val EMPTY = ""
        const val SIGN_IN = "Войти"
        const val SIGN_UP = "Регистрация"
        const val LOGIN_V = "Вход"
        const val LOGIN_TEXT = "Логин"
        const val PASS_TEXT = "Пароль"
    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}