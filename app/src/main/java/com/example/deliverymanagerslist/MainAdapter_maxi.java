package com.example.deliverymanagerslist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter_maxi extends FirebaseRecyclerAdapter<MainModel_maxi, MainAdapter_maxi.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter_maxi(@NonNull FirebaseRecyclerOptions<MainModel_maxi> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull MainModel_maxi model) {

        holder.name.setText(model.getName());
        holder.company.setText(model.getCompany());
        holder.tel.setText(model.getTel());
        holder.date.setText(model.getDate());

        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();
                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.txtName);
                EditText company = view.findViewById(R.id.txtCom);
                EditText tel = view.findViewById(R.id.txtTel);
                EditText date = view.findViewById(R.id.txtDate);
                EditText image = view.findViewById(R.id.txtImageUrl);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                name.setText(model.getName());
                company.setText(model.getCompany());
                tel.setText(model.getTel());
                date.setText(model.getDate());
                image.setText(model.getImage());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("company",company.getText().toString());
                        map.put("tel",tel.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("image",image.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("manager")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data Update Sucessfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                               .addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(Exception e) {
                                       Toast.makeText(holder.name.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                       dialogPlus.dismiss();
                                   }
                               });


                    }
                });

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                        builder.setTitle("Are you sure");
                        builder.setMessage("Deleted data can't be undo");

                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            FirebaseDatabase.getInstance().getReference().child("manager")
                                    .child(getRef(position).getKey()).removeValue();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();

                    }
                });



            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        Button btnEdit,btnDelete;

        CircleImageView img;
        TextView name,company,tel,date;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            company = (TextView) itemView.findViewById(R.id.comtext);
            tel = (TextView)itemView.findViewById(R.id.teltext);
            date = (TextView)itemView.findViewById(R.id.dtext);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);

        }
    }

}
