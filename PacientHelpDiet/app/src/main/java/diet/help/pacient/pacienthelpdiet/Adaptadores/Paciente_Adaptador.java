package diet.help.pacient.pacienthelpdiet.Adaptadores;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import diet.help.pacient.pacienthelpdiet.Interface.OnSelectElementos;
import diet.help.pacient.pacienthelpdiet.Modelos.Hospitalizacion;
import diet.help.pacient.pacienthelpdiet.Modelos.Paciente;
import diet.help.pacient.pacienthelpdiet.R;

/**
 * Created by mauuu on 18/1/2018.
 */

public class Paciente_Adaptador extends RecyclerView.Adapter<Paciente_Adaptador.PacienteViewHolder>{

    ArrayList<Hospitalizacion> hospitalizacions;
    private OnSelectElementos onSelectElementos;

    public Paciente_Adaptador(ArrayList<Hospitalizacion> hospitalizacions) {
        this.hospitalizacions = hospitalizacions;
    }

    public void setOnSelectElementos(OnSelectElementos onSelectElementos) {
        this.onSelectElementos = onSelectElementos;
    }

    @Override
    public Paciente_Adaptador.PacienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.paciente,parent,false);
        return new Paciente_Adaptador.PacienteViewHolder(v,onSelectElementos);
    }

    @Override
    public void onBindViewHolder(final Paciente_Adaptador.PacienteViewHolder holder, final int position) {
        Hospitalizacion pacientes=hospitalizacions.get(position);
        for(int i=0;i<hospitalizacions.size();i++) {
            Glide.with(holder.itemView.getContext()).load(pacientes.getPacientes().get(i).getImg()).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img);
            holder.nombre.setText(pacientes.getPacientes().get(i).getNombre()+" "+pacientes.getPacientes().get(i).getApellido());
        }

    }

    @Override
    public int getItemCount() {
        return hospitalizacions.size();
    }

    public static class PacienteViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView nombre;
        CardView btn_add;
        public PacienteViewHolder(View itemView, final OnSelectElementos onSelectElementos) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.ig_img);
            nombre=(TextView) itemView.findViewById(R.id.tv_nombre_paciente);
            btn_add=(CardView)itemView.findViewById(R.id.cv_send);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion=getAdapterPosition();
                    if(posicion!=RecyclerView.NO_POSITION){
                        onSelectElementos.onAddClick(posicion);
                    }
                }
            });
        }

    }
}
