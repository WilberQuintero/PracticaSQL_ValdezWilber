package valdez.wilber.practicasql_valdezwilber

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "practica_sql.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_ESTUDIANTES = "estudiantes"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_APELLIDO_PATERNO = "apellido_paterno"
        const val COLUMN_APELLIDO_MATERNO = "apellido_materno"
        const val COLUMN_CARRERA = "carrera"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_ESTUDIANTES = """
            CREATE TABLE $TABLE_ESTUDIANTES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_APELLIDO_PATERNO TEXT NOT NULL,
                $COLUMN_APELLIDO_MATERNO TEXT NOT NULL,
                $COLUMN_CARRERA TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(CREATE_TABLE_ESTUDIANTES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // para la practica,  eliminamos y recreamos la tabla.
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ESTUDIANTES")
        onCreate(db)
    }
}