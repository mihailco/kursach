package harakiri.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import harakiri.entity.CourseInfoResponse;
import harakiri.entity.MarkedCoursesResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PDFGeneratorService {
    private final UserServiceRemote userServiceRemote;
    private final CourseServiceRemote courseServiceRemote;


    public void cliEntReport(HttpServletResponse response, long userId) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontHeader = new Font(getBaseFont(), 18);
        Font fontParagraph = new Font(getBaseFont(), 14);

        Paragraph headerParagraph = new Paragraph("Выписка из личного кабинета", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        List<String> t = Arrays.stream(userServiceRemote.getMarkedCourses(userId)).map(MarkedCoursesResponse::getId).toList();
        var courses = courseServiceRemote.getMarkedCourses(t);
        for (CourseInfoResponse i : courses) {
            Paragraph pdfParagraph = new Paragraph(i.toString(), fontParagraph);
            pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);

            document.add(pdfParagraph);
        }

        document.add(headerParagraph);
        document.close();
    }

    public void priceList(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontHeader = new Font(getBaseFont(), 18);
        Font fontParagraph = new Font(getBaseFont(), 14);


        Paragraph headerParagraph = new Paragraph("Выписка из личного кабинета", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(headerParagraph);
        var courses = courseServiceRemote.getCourses();
        for (CourseInfoResponse i : courses) {
            Paragraph pdfParagraph = new Paragraph(i.getTittle(), fontHeader);
            pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(new Paragraph(i.getPrice().toString(), fontParagraph));
            document.add(new Paragraph(i.getFIO(), fontParagraph));
            document.add(new Paragraph(i.getCreatedAt().toString(), fontParagraph));
            document.add(new Paragraph(i.getCourseFor().toString(), fontParagraph));
            document.add(new Paragraph(i.getLearningResults().toString(), fontParagraph));

            document.add(new Paragraph("\n", fontHeader));

            document.add(pdfParagraph);
        }


        document.close();
    }

    public void revenueReport(HttpServletResponse response, Date start, Date end) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontHeader = new Font(getBaseFont(), 18);
        Font fontParagraph = new Font(getBaseFont(), 14);

        Paragraph headerParagraph = new Paragraph("отчет по выручке", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        List<MarkedCoursesResponse> c = Arrays.stream(userServiceRemote.getAllMarkedCourses())
                .filter(x -> x.getDate().before(end) && x.getDate().after(start)).toList();

        double price = 0;
        for (MarkedCoursesResponse i : c) {
            Paragraph pdfParagraph = new Paragraph(i.toString(), fontParagraph);
            pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);
            price += i.getPrice();
            document.add(pdfParagraph);
        }

        Paragraph footerParagraph = new Paragraph("Всего было получено ", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(headerParagraph);
        document.close();
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontHeader.setSize(22);

        Paragraph headerParagraph = new Paragraph("## PDF Heading ##", fontHeader);
        headerParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.TIMES);
        fontParagraph.setSize(14);

        Paragraph pdfParagraph = new Paragraph("*** PDF Paragraph ***", fontParagraph);
        pdfParagraph.setAlignment(Paragraph.ALIGN_LEFT);
        document.setDocumentLanguage("ru");
        document.add(headerParagraph);
        document.add(pdfParagraph);
        document.close();
    }


    public BaseFont getBaseFont() throws IOException {
        return
                BaseFont.createFont("C:\\Users\\mihai\\IdeaProjects\\kursach2\\file-service\\src\\main\\resources\\Alice-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    }
}
