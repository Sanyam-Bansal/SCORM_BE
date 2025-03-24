package com.gemini.scorm.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.gemini.scorm.model.ScormCourse;
import com.gemini.scorm.repository.ScormCourseRepository;

@Service
public class ScormCourseServiceImpl implements ScormCourseService {

	@Autowired
	private ScormCourseRepository repository;

	private final String BASE_DIR = "C:/scorm-content/";

	public ScormCourse saveAndExtractScorm(MultipartFile file) throws IOException {
		String originalFileName = file.getOriginalFilename();
		String courseName = FilenameUtils.getBaseName(originalFileName).replace(" ", "_");
//		String courseName = FilenameUtils.getBaseName(originalFileName);
		Path courseDir = Paths.get(BASE_DIR, courseName);

		if (Files.exists(courseDir)) {
			deleteDirectory(courseDir.toFile());
		}

		Files.createDirectories(courseDir);

		try (ZipInputStream zis = new ZipInputStream(file.getInputStream())) {
			ZipEntry zipEntry;
			while ((zipEntry = zis.getNextEntry()) != null) {
				Path newFilePath = courseDir.resolve(zipEntry.getName());

				if (zipEntry.isDirectory()) {
					Files.createDirectories(newFilePath);
				} else {
					Files.createDirectories(newFilePath.getParent());
					Files.copy(zis, newFilePath, StandardCopyOption.REPLACE_EXISTING);
				}
				zis.closeEntry();
			}
		}

		String title = extractCourseTitle(courseDir);
		String launchUrl = "/scorm-content/" + courseName + "/course/index.html";
		ScormCourse course = new ScormCourse();
		course.setCourseName(courseName);
		course.setFilePath(launchUrl);
		course.setTitle(title);
		return repository.save(course);
	}

	private String extractCourseTitle(Path courseDir) {
		Path manifestPath = courseDir.resolve("imsmanifest.xml");

		if (!Files.exists(manifestPath)) {
			return "Unknown Course";
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(manifestPath.toFile());

			NodeList titleNodes = doc.getElementsByTagName("title");
			if (titleNodes.getLength() > 0) {
				return titleNodes.item(0).getTextContent().trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unknown Course";
	}

	private void deleteDirectory(File dir) throws IOException {
		if (dir.isDirectory()) {
			for (File child : dir.listFiles()) {
				deleteDirectory(child);
			}
		}
		if (!dir.delete()) {
			throw new IOException("Failed to delete " + dir.getAbsolutePath());
		}
	}

	public ScormCourse saveCourse(ScormCourse course) {
		return repository.save(course);
	}

	public List<ScormCourse> getAllCourses() {
		return repository.findAll();
	}

	public Optional<ScormCourse> getCourseById(Long id) {
		return repository.findById(id);
	}

	public boolean deleteCourse(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
