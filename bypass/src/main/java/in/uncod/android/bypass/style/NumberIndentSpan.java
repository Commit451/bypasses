package in.uncod.android.bypass.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

public class NumberIndentSpan implements LeadingMarginSpan {

    private final int leadWidth;
    private final int index;

    public NumberIndentSpan(int leadGap, int index) {
        this.leadWidth = leadGap;
        this.index = index;
    }

    public int getLeadingMargin(boolean first) {
        return leadWidth;
    }

    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top,
                                  int baseline, int bottom, CharSequence text, int start, int end,
                                  boolean first, Layout l) {
        if (first) {
            Paint.Style orgStyle = p.getStyle();
            p.setStyle(Paint.Style.FILL);
            float width = p.measureText("5. ");
            c.drawText(index + ".", (x + (leadWidth-width)) * dir, baseline, p);
            p.setStyle(orgStyle);
        }
    }
}