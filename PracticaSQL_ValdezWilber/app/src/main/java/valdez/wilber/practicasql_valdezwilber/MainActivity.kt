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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import valdez.wilber.practicasql_valdezwilber.data.AlumnoSQLHelper

class MainActivity : AppCompatActivity() {

    private lateinit var db: AlumnoSQLHelper

    private lateinit var etNombre: EditText
    private lateinit var etApellidoPaterno: EditText
    private lateinit var etApellidoMaterno: EditText
    private lateinit var etCarrera: EditText
    private lateinit var btnGuardar: Button
    private lateinit var dbHelper: DatabaseHelper

    private lateinit var recyclerView: RecyclerView
    private lateinit var alumnosAdapter: AlumnoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        db = AlumnoSQLHelper(this)


//        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre)
        etApellidoPaterno = findViewById(R.id.etApellidoPaterno)
        etApellidoMaterno = findViewById(R.id.etApellidoMaterno)
        etCarrera = findViewById(R.id.etCarrera)
        btnGuardar = findViewById(R.id.btnGuardar)

        recyclerView = findViewById(R.id.studentsList)
        recyclerView.layoutManager = LinearLayoutManager(this)


        fun loadAlumnosIntoRecyclerView() {
            val alumnosList = db.getAllAlumnos()
                alumnosAdapter = AlumnoAdapter(alumnosList)
                recyclerView.adapter = alumnosAdapter

        }



   btnGuardar.setOnClickListener() {
        val nombre = etNombre.text.toString().trim()
        val apellidoPaterno = etApellidoPaterno.text.toString().trim()
        val apellidoMaterno = etApellidoMaterno.text.toString().trim()
        val carrera = etCarrera.text.toString().trim()

    val alumno = Alumno(nombre, apellidoPaterno, apellidoMaterno, carrera)

    db.insertAlumno(alumno)
    Toast.makeText(this,"Alumno guardado", Toast.LENGTH_SHORT).show()

        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() || carrera.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }

             loadAlumnosIntoRecyclerView()
        }
    }
}