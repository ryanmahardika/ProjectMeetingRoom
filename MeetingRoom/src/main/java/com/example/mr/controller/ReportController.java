package com.example.mr.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.mr.model.DataKerusakan;
import com.example.mr.model.DataPengajuan;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@Controller
@SessionAttributes("penggunaaktif")
public class ReportController {

	@Autowired
	private DataSource dataSource;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	
	@RequestMapping(value ="/downloadpdfkerusakan",method=RequestMethod.GET)
    public String cetakPemakaianPDF(@ModelAttribute("datakerusakan") DataKerusakan dk, HttpServletResponse response) throws IOException {
		InputStream kerusakanReportStream = getClass().getResourceAsStream("/HistoriKerusakan.jrxml");
		
		try {
			jasperReport = JasperCompileManager.compileReport(kerusakanReportStream);
		} catch (JRException ex) {
			ex.printStackTrace();
		}
		
		JRPdfExporter exporter = new JRPdfExporter();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Laporan Histori Kerusakan");
		parameters.put("condition", "SELECT dk.id, dk.kode_kerusakan, r.nama_ruangan, dk.tanggal_rusak, dk.deskripsi, dk.fasilitas_rusak, dk.status_perbaikan FROM datakerusakan dk JOIN ruangan r WHERE dk.id_ruangan = r.id");
		 
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=LaporanHistoriKerusakan.pdf");
			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		} catch (JRException | SQLException ex) {
			ex.printStackTrace();
		}
		
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("LaporanHistoriKerusakan.pdf"));
		 
		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);
		 
		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("MeetingRoom");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");
		 
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);
		 
		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return "redirect:historikerusakan";
	}
	
	@RequestMapping(value ="/downloadpdfpemakaian",method=RequestMethod.GET)
    public String cetakKerusakanPDF(@ModelAttribute("datapengajuan") DataPengajuan dp, HttpServletResponse response) throws IOException {
		InputStream kerusakanReportStream = getClass().getResourceAsStream("/HistoriPemakaian.jrxml");
		
		try {
			jasperReport = JasperCompileManager.compileReport(kerusakanReportStream);
		} catch (JRException ex) {
			ex.printStackTrace();
		}
		
		JRPdfExporter exporter = new JRPdfExporter();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Laporan Histori Pemakaian");
		parameters.put("condition", "SELECT dp.id, k.nama, r.nama_ruangan, dp.tanggal_pemakaian, CONCAT(dp.mulai, ' - ' ,dp.selesai) as jam_pemakaian, dp.subjek_meeting, dp.status_pengajuan FROM datapengajuan dp, karyawan k, ruangan r WHERE dp.id_karyawan=k.id AND dp.id_ruangan=r.id AND dp.status_pengajuan='Selesai'");
		 
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=LaporanHistoriPemakaian.pdf");
			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		} catch (JRException | SQLException ex) {
			ex.printStackTrace();
		}
		
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("LaporanHistoriPemakaian.pdf"));
		 
		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);
		 
		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("MeetingRoom");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");
		 
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);
		 
		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return "redirect:historipemakaian";
	}
	
}
