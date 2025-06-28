package valdez.wilber.practicasql_valdezwilber.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import valdez.wilber.practicasql_valdezwilber.Alumno

class AlumnoSQLHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
    private const val DATABASE_NAME = "alumnos.db"
    private const val DATABASE_VERSION = 1
    private const val TABLE_NAME = "alumnos"
    private const val COLUMN_ID = "_id"
    private const val COLUMN_NAME = "name"
    private const val COLUMN_FIRST_LASTNAME = "first_last_name"
    private const val COLUMN_SECOND_LASTNAME = "second_last_name"
    private const val COLUMN_EDU_PROGRAM = "educational_program"
    }

    override fun onCreate(db: SQLiteDatabase?) {
    val createTableAlumnos = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, " +
            "$COLUMN_NAME TEXT," +
            "$COLUMN_FIRST_LASTNAME TEXT," +
            "$COLUMN_SECOND_LASTNAME TEXT," +
            "$COLUMN_EDU_PROGRAM TEXT" +
            ")"
        db?.execSQL(createTableAlumnos)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            val dropTRableAlumnos = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTRableAlumnos)
        onCreate(db)

    }


    fun insertAlumno(alumno: Alumno) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, alumno.nombre)
            put(COLUMN_FIRST_LASTNAME,alumno.apellidoPaterno)
            put(COLUMN_SECOND_LASTNAME, alumno.apellidoMaterno)
            put(COLUMN_EDU_PROGRAM, alumno.carrera)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()

    }

    fun getAllAlumnos(): List<Alumno> {
        val alumnosList = mutableListOf<Alumno>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val apPaterno = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_LASTNAME))
            val apMaterno = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SECOND_LASTNAME))
            val carrera = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EDU_PROGRAM))

            val alumno = Alumno(nombre, apPaterno, apMaterno, carrera)
            alumnosList.add(alumno)
        }
            cursor.close()
            db.close()
        return alumnosList

    }


}