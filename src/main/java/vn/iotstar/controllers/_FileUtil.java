package vn.iotstar.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

final class _FileUtil {
	private _FileUtil() {}
	
	static String save(InputStream in, String originalName, String targetDir) throws IOException {
		Path dir = Paths.get(targetDir);
		Files.createDirectories(dir);
		String base = (originalName == null || originalName.isEmpty()) ? "file" : originalName;
		String ext = "";
		int dot = base.lastIndexOf('.');
		if (dot >= 0) {
			ext = base.substring(dot);
			base = base.substring(0, dot);
		}
		String stored = base + "_" + UUID.randomUUID().toString().replace("-", "") + ext;
		Path dest = dir.resolve(stored);
		try (FileOutputStream out = new FileOutputStream(dest.toFile())) {
			byte[] buf = new byte[8192];
			int r;
			while ((r = in.read(buf)) != -1) {
				out.write(buf, 0, r);
			}
		}
		return stored;
	}
	
	static void deleteIfExists(String path) {
		if (path == null || path.isEmpty()) return;
		File f = new File(path);
		if (f.isAbsolute()) {
			f.delete();
		} else {
			String[] guesses = {"uploads/icons", "uploads", "images/icons", "images"};
			for (String g : guesses) {
				File candidate = new File(g, path);
				if (candidate.exists()) {
					candidate.delete();
					break;
				}
			}
		}
	}
}
