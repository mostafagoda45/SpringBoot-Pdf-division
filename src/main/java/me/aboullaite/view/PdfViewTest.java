package me.aboullaite.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import me.aboullaite.model.Employee;

public class PdfViewTest extends AbstractPdfView {


    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);

    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);

    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public static final String TEXT = "This is some long paragraph that will be added over and over again to prove a point.";

    public static final float COLUMN_WIDTH = 254;

    public static final float ERROR_MARGIN = 16;

    public static final Rectangle[] COLUMNS = { new Rectangle(36, 36, 36 + COLUMN_WIDTH, 806), new Rectangle(305, 36, 305 + COLUMN_WIDTH, 806) };
    public static final String[] LANGUAGES = { "la", "en", "fr" };

    public static final Rectangle[] RECTANGLES = { new Rectangle(36, 581, 123, 700), new Rectangle(270, 581, 806, 700), new Rectangle(36, 200, 559, 500) };

    @Override
    protected void buildPdfDocument(final Map<String, Object> model, final Document document, final PdfWriter writer, final HttpServletRequest request,
            final HttpServletResponse response)
                    throws Exception {
        response.setHeader("Content-Disposition", "inline; filename=\"my-pdf-file.pdf\"");
        final List<Employee> emp = (List<Employee>) model.get("users");
        final PdfContentByte canvas = writer.getDirectContent();
        final ColumnText ct = new ColumnText(canvas);
        new Phrase("header");

        for (final Employee e : emp) {
            addContent(canvas, ct, e, document);
            float height = getNecessaryHeight(ct);
            // float height = 270;
            addContent(canvas, ct, e, document);
            Rectangle left;
            final float top = COLUMNS[0].getTop();
            final float middle = (COLUMNS[0].getLeft() + COLUMNS[1].getRight()) / 2;
            float columnheight;
            int status = ColumnText.START_COLUMN;
            while (ColumnText.hasMoreText(status)) {
                if (checkHeight(height)) {
                    columnheight = COLUMNS[0].getHeight();
                    left = COLUMNS[0];
                } else {
                    columnheight = height / 2 + ERROR_MARGIN;
                    left = new Rectangle(COLUMNS[0].getLeft(), COLUMNS[0].getTop() - columnheight, COLUMNS[0].getRight(), COLUMNS[0].getTop());
                }
                // left half
                ct.setSimpleColumn(left);
                ct.go();
                height -= COLUMNS[0].getTop() - ct.getYLine();
                // separator
                canvas.moveTo(middle, top - columnheight);
                canvas.lineTo(middle, top);
                canvas.stroke();
                // right half
                ct.setSimpleColumn(COLUMNS[1]);
                status = ct.go();
                height -= COLUMNS[1].getTop() - ct.getYLine();
                // new page
                break;
            }

            document.newPage();
        }
    }



    private boolean checkHeight(float height) {
        height -= COLUMNS[0].getHeight() + COLUMNS[1].getHeight() + ERROR_MARGIN;
        return height > 0;
    }

    private float getNecessaryHeight(final ColumnText ct) throws DocumentException {
        ct.setSimpleColumn(new Rectangle(0, 0, COLUMN_WIDTH, -500000));
        ct.go(true);
        return -ct.getYLine();
    }

    private void addContent(final PdfContentByte canvas,final ColumnText ct, final Employee e, final Document document) throws DocumentException {
    	float x = document.right() / 2 ;
    	float y = document.top() + 25;
    	float o = 0;
    	Chunk c = new Chunk("Header");
    	c.setBackground(BaseColor.LIGHT_GRAY);
    	c.setFont(new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLDITALIC));
    	Phrase ph = new Phrase(c);
    	ct.showTextAligned(canvas, Element.ALIGN_CENTER, ph, x, y, o);
        final Anchor anchor = new Anchor("Chapter", catFont);
        anchor.setName("Chapter");
        ct.addElement(new Paragraph(anchor));

        if (!e.getEmployeeEmail().equals("")) {
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));
            ct.addElement(new Paragraph("Paragraph 1   " + e.getEmployeeEmail()));


        }
        if (e.getEmployeeLastName() != null && !e.getEmployeeLastName().equals("")) {
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));
            ct.addElement(new Paragraph("Paragraph 3   " + e.getEmployeeLastName()));

        }
        if (!e.getEmployeeFirstName().equals("")) {
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));
            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));

            ct.addElement(new Paragraph("Paragraph 2   " + e.getEmployeeFirstName()));

        }
    }
}
