package lt.notesapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import lt.notesapp.databinding.ViewAddEditGroupBinding;

public class AddEditGroupView extends LinearLayout {

    private ViewAddEditGroupBinding binding;

    public AddEditGroupView(Context context) {
        super(context);
        init(context);
    }

    public AddEditGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddEditGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ViewAddEditGroupBinding.inflate(inflater, this, true);
    }
}
