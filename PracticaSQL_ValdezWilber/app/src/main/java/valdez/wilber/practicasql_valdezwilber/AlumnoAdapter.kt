package valdez.wilber.practicasql_valdezwilber

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class AlumnoAdapter(private var alumnosList: MutableList<Alumno>, private val context: Context):
    RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder>() {

    class AlumnoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCareer: TextView = itemView.findViewById(R.id.tvCareer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alumno, parent, false)
        return AlumnoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val currentAlumno = alumnosList[position]
        holder.tvName.text = "${currentAlumno.nombre} ${currentAlumno.apellidoPaterno} ${currentAlumno.apellidoMaterno}"
        holder.tvCareer.text = currentAlumno.carrera
    }

    override fun getItemCount(): Int {
        return alumnosList.size
    }


    fun updateData(newAlumnos: List<Alumno>) {
        this.alumnosList.clear() 
        this.alumnosList.addAll(newAlumnos)
        notifyDataSetChanged()
    }
}
