package zeddysoft.com.javdev.javadevListing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zeddysoft.com.javdev.R;
import zeddysoft.com.javdev.interfaces.RecyclerViewClickListener;
import zeddysoft.com.javdev.models.JavaDev;

/**
 * Created by azeez on 4/21/17.
 */

public class JavaDevListingAdapter extends RecyclerView.Adapter<JavaDevListingAdapter.JavaDevItemHolder> {

    private final List<JavaDev> javaDevs;
    private final Context context;
    private final RecyclerViewClickListener clickListener;

    public JavaDevListingAdapter(List<JavaDev> javaDevs,
                                 Context context,
                                 RecyclerViewClickListener recyclerViewClickListener) {
        this.javaDevs = javaDevs;
        this.context = context;
        this.clickListener = recyclerViewClickListener;
    }

    @Override
    public JavaDevItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.java_dev_list_item, parent, false);

        return new JavaDevItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JavaDevItemHolder holder, int position) {

        JavaDev javaDev = javaDevs.get(position);

        Picasso.with(context)
                .load(javaDev.getAvatarUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.profileImageView);

        holder.usernameView.setText(javaDev.getUsername());
    }

    @Override
    public int getItemCount() {
        return javaDevs.size();
    }

    public class JavaDevItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView profileImageView;
        private final TextView usernameView;

        public JavaDevItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            profileImageView = (ImageView) itemView.findViewById(R.id.profile_image);
            usernameView = (TextView) itemView.findViewById(R.id.github_username);
        }


        @Override
        public void onClick(View v) {
            clickListener.recyclerViewListClicked(v, javaDevs.get(getLayoutPosition()));
        }
    }
}