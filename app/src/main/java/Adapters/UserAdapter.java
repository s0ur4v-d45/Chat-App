package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatapp.MainActivity;
import com.example.chatapp.MessageActivity;
import com.example.chatapp.Model.User;
import com.example.chatapp.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder>{

    private Context mContext;
    private List<User> mUsers;
    private boolean ischat;

    public UserAdapter(Context mContext,List<User> mUsers,boolean ischat) {
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.ischat=ischat;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        User user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        if(user.getImageURL().equals("default"))
        {
            holder.profile_picture.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_picture);
        }

        if(ischat)
        {
            if(user.getStatus().equals("online"))
            {
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_off.setVisibility(View.GONE);
            }
            else
            {
                holder.img_on.setVisibility(View.GONE);
                holder.img_off.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            holder.img_on.setVisibility(View.GONE);
            holder.img_off.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid",user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        public TextView username;
        public ImageView profile_picture;
        private ImageView img_on;
        private ImageView img_off;

        public Viewholder(View itemView)
        {
            super(itemView);
            username=(TextView) itemView.findViewById(R.id.username);
            profile_picture=(ImageView) itemView.findViewById(R.id.profile_picture);

            img_on=(ImageView) itemView.findViewById(R.id.img_on);
            img_off=(ImageView) itemView.findViewById(R.id.img_off);
        }
    }

}
