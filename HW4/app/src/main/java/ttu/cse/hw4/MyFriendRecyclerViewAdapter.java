package ttu.cse.hw4;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ttu.cse.hw4.databinding.FragmentItemBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyFriendRecyclerViewAdapter extends RecyclerView.Adapter<MyFriendRecyclerViewAdapter.ViewHolder> implements View.OnLongClickListener {

    private List<String> starCH;
    private List<String> starEN;

    private List<Friend> friendList = new ArrayList<>();
    public MyFriendRecyclerViewAdapter(List<Friend> List) {
        //starCH = ch;
        //starEN = en;
        friendList=List;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // holder.mItem = mValues.get(position);
        holder.mCH.setText(friendList.get(position).ZodiacC);

        holder.mEN.setText(friendList.get(position).ZodiacE);
        holder.date.setText(friendList.get(position).date);
        holder.icon.setImageResource(friendList.get(position).icon);

        holder.icon.setOnLongClickListener(this);
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public final TextView mCH;
        public final TextView mEN;
        public final TextView date;

        public final ImageView icon ;
        //public final ImageView icon;
        //public PlaceholderItem mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mCH = binding.itemCh;
            mEN= binding.itemEn;
            date=binding.date;
            icon=binding.icon;

            binding.getRoot().setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            AlertDialog.Builder myDlg = new AlertDialog.Builder(v.getContext()); // MainActivity是情況改為你的Activity類別名稱

            myDlg.setIcon(icon.getDrawable()).setTitle(mCH.getText()+"("+mEN.getText()+")").setItems(R.array.alert, new DialogInterface.OnClickListener()
            {


                public void onClick(DialogInterface dialog, int which)
                {
                    // The 'which' argument contains the index position
                    // of the selected item
                    if(which==0){
                        friendList.remove(getAdapterPosition());
                    }
                    else if(which==1){
                        if(getAdapterPosition()!=0){
                            Collections.swap(friendList,getAdapterPosition()-1,getAdapterPosition());
                        }
                    }
                    else if(which==2){
                        if(getAdapterPosition()!=friendList.size()-1){
                            Collections.swap(friendList,getAdapterPosition(),getAdapterPosition()+1);
                        }
                    }

                    notifyDataSetChanged();

                }
            });

            myDlg.show();

            return false;
        }
    }
}