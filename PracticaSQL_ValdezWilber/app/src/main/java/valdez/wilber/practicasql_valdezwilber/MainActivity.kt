package valdez.wilber.practicasql_valdezwilber

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etApellidoPaterno: EditText
    private lateinit var etApellidoMaterno: EditText
    private lateinit var etCarrera: EditText
    private lateinit var btnGuardar: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre)
        etApellidoPaterno = findViewById(R.id.etApellidoPaterno)
        etApellidoMaterno = findViewById(R.id.etApellidoMaterno)
        etCarrera = findViewById(R.id.etCarrera)
        btnGuardar = findViewById(R.id.btnGuardar)


        // Inicializar el helper de la base de datos
        dbHelper = DatabaseHelper(this)

        btnGuardar.setOnClickListener {
            guardarDatosEstudiante()
        }
    }

    private fun guardarDatosEstudiante() {
        val nombre = etNombre.text.toString().trim()
        val apellidoPaterno = etApellidoPaterno.text.toString().trim()
        val apellidoMaterno = etApellidoMaterno.text.toString().trim()
        val carrera = etCarrera.text.toString().trim()

        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() || carrera.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // obtiene una instancia de la base de datos
        val db = dbHelper.writableDatabase

        // crea un objeto ContentValues para insertar los datos
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NOMBRE, nombre)
            put(DatabaseHelper.COLUMN_APELLIDO_PATERNO, apellidoPaterno)
            put(DatabaseHelper.COLUMN_APELLIDO_MATERNO, apellidoMaterno)
            put(DatabaseHelper.COLUMN_CARRERA, carrera)
        }

        // inserta los datos en la tabla
        val newRowId = db.insert(DatabaseHelper.TABLE_ESTUDIANTES, null, values)

        // cierra la conexion a la base de datos
        db.close()

        if (newRowId != -1L) {
            Toast.makeText(this, "Datos guardados correctamente con ID: $newRowId", Toast.LENGTH_LONG).show()
            // limpiar los campos después de guardar
            etNombre.text.clear()
            etApellidoPaterno.text.clear()
            etApellidoMaterno.text.clear()
            etCarrera.text.clear()
        } else {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
        }
    }
}