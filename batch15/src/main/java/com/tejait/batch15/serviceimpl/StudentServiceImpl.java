package com.tejait.batch15.serviceimpl;

import java.io.ByteArrayOutputStream;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.tejait.batch15.model.Student;
import com.tejait.batch15.repository.StudentRepository;
import com.tejait.batch15.service.StudentService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{
	//@Autowired
StudentRepository repository;
	@Override
	public Student saveStudent(Student student) {
		String fname= student.getFname();
		String lname= student.getLname();
		student.setFullname(fname.concat(" "+lname));
		return repository.save(student);
	}
	@Override
	public void deleteStudent(Integer id) {
		
		repository.deleteById(id);
	}
	@Override
	public Optional<Student> getById(Integer id) {
		
		return repository.findById(id);
	}
	@Override
	public List<Student> getAllStudents() {
		
		return repository.findAll();
	}
	//==========textfile=======================
	@Override
	public byte[] generateTextFile(Integer id) {
		Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        String data = "Student Details\n" +
                "ID: " + student.getId() + "\n" +
                "Name: " + student.getFullname() + "\n" +
                "First Name: " + student.getFname() + "\n" +
                "Last Name: " + student.getLname() + "\n" +
                "Age: " + student.getAge() + "\n" +
                "Course: " + student.getCource() + "\n" +
                "Fee: " + student.getFee() + "\n" +
                "Student Code: " + student.getStuCode();

        return data.getBytes(StandardCharsets.UTF_8);
	}
	//=========pdffile============
	@Override
	public byte[] generateAllStudentsPdf() {
		List<Student> students = repository.findAll();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("STUDENT DETAILS\n\n"));;

            for (Student student : students) {

                document.add(new Paragraph("ID: " + student.getId()));
                document.add(new Paragraph("Name: " + student.getFullname()));
                document.add(new Paragraph("First Name: " + student.getFname()));
                document.add(new Paragraph("Last Name: " + student.getLname()));
                document.add(new Paragraph("Age: " + student.getAge()));
                document.add(new Paragraph("Course: " + student.getCource()));
                document.add(new Paragraph("Fee: " + student.getFee()));
                document.add(new Paragraph("Student Code: " + student.getStuCode()));
            }

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
	//==========wordfile==========================
	@Override
	public byte[] generateStudentsWord() {
		 List<Student> students = repository.findAll();

	        try (XWPFDocument document = new XWPFDocument();
	             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

	            // Title
	            XWPFParagraph title = document.createParagraph();
	            XWPFRun run = title.createRun();
	            run.setBold(true);
	            run.setFontSize(16);
	            run.setText("STUDENT DETAILS");

	            for (Student student : students) {

	                XWPFParagraph para = document.createParagraph();
	                XWPFRun r = para.createRun();

	                r.setText("ID: " + student.getId());
	                r.addBreak();
	                r.setText("Name: " + student.getFullname());
	                r.addBreak();
	                r.setText("First Name: " + student.getFname());
	                r.addBreak();
	                r.setText("Last Name: " + student.getLname());
	                r.addBreak();
	                r.setText("Age: " + student.getAge());
	                r.addBreak();
	                r.setText("Course: " + student.getCource());
	                r.addBreak();
	                r.setText("Fee: " + student.getFee());
	                r.addBreak();
	                r.setText("Student Code: " + student.getStuCode());
	                r.addBreak();
	                r.setText("-----------------------------");
	                r.addBreak();
	            }

	            document.write(out);
	            return out.toByteArray();

	        } catch (Exception e) {
	            throw new RuntimeException("Error generating Word file", e);
	        }
	    }
	//===========exxelfile=========================
	@Override
	public byte[] generateStudentsExcel() {

	    List<Student> students = repository.findAll();

	    try (Workbook workbook = new XSSFWorkbook();
	         ByteArrayOutputStream out = new ByteArrayOutputStream()) {

	        Sheet sheet = workbook.createSheet("Students");

	        // Header row
	        Row header = sheet.createRow(0);
	        header.createCell(0).setCellValue("ID");
	        header.createCell(1).setCellValue("Full Name");
	        header.createCell(2).setCellValue("First Name");
	        header.createCell(3).setCellValue("Last Name");
	        header.createCell(4).setCellValue("Age");
	        header.createCell(5).setCellValue("Course");
	        header.createCell(6).setCellValue("Fee");
	        header.createCell(7).setCellValue("Student Code");

	        // Data rows
	        int rowNum = 1;
	        for (Student s : students) {
	            Row row = sheet.createRow(rowNum++);

	            row.createCell(0).setCellValue(s.getId());
	            row.createCell(1).setCellValue(s.getFullname());
	            row.createCell(2).setCellValue(s.getFname());
	            row.createCell(3).setCellValue(s.getLname());
	            row.createCell(4).setCellValue(s.getAge());
	            row.createCell(5).setCellValue(s.getCource()); // your field name
	            row.createCell(6).setCellValue(s.getFee());
	            row.createCell(7).setCellValue(s.getStuCode());
	        }

	        // Auto size columns (AFTER loop)
	        for (int i = 0; i < 8; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Write once (AFTER loop)
	        workbook.write(out);

	        return out.toByteArray();

	    } catch (Exception e) {
	        throw new RuntimeException("Error generating Excel file", e);
	    }
	}
}
	