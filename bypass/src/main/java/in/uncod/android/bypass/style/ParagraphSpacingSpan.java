package in.uncod.android.bypass.style;

import android.graphics.Paint;
import android.text.Spanned;
import android.text.style.LineHeightSpan;

public class ParagraphSpacingSpan implements LineHeightSpan {

    private final int bottomSpacing;

    public ParagraphSpacingSpan(int bottomSpacing){
        this.bottomSpacing = bottomSpacing;
    }

    @Override
    public void chooseHeight(CharSequence text, int start, int end,
                             int spanstartv, int v, Paint.FontMetricsInt fm) {
        Spanned spanned = (Spanned) text;
        int st = spanned.getSpanStart(this);
        int en = spanned.getSpanEnd(this);
        if (end == en) {
            fm.descent += bottomSpacing;
            fm.bottom += bottomSpacing;
        }
    }
}
