package az.altacademy.androidgroup2.lessons.lesson21

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecoration(
    private var space: Int
): ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

//        if (parent.getChildAdapterPosition(view) != 0) {
//
//        }

        outRect.top = space
//        outRect.bottom = space
        outRect.left = space
        outRect.right = space


//        with(outRect) {
//            top = 0
//            if (parent.getChildAdapterPosition(view) != 0) {
//                top = verticalSpace
//            }else if (addTopSpaceFirstView){
//                top = verticalSpace
//            }
//            bottom = 0
//
//            left = 0
//            if (parent.getChildAdapterPosition(view) != 0) {
//                left = horizontalSpace
//            }else if (addLeftSpaceFirstView){
//                left = horizontalSpace
//            }
//            right = 0
//        }
    }


}