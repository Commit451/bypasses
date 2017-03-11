package in.uncod.android.bypass;

import android.text.style.ImageSpan;
import android.view.View;

/**
 * Callback when the image span is clicked
 */
public interface ImageSpanClickListener {

    /**
     * Indicates that an image span has been clicked
     *
     * @param view the view
     * @param imageSpan the imageSpan
     * @param span      the string
     */
    void onImageClicked(View view, ImageSpan imageSpan, String span);
}
