package in.uncod.android.bypass.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

public class CharacterIndentSpan implements LeadingMarginSpan {

    private final int leadWidth;
    private final String character;

    public CharacterIndentSpan(int leadGap, String character) {
        this.leadWidth = leadGap;
        this.character = character;
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
            float width = p.measureText(character + " ");
            c.drawText(character, (x + (leadWidth-width)) * dir, baseline, p);
            p.setStyle(orgStyle);
        }
    }
}