
package minhui.demo.sodusave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import minhui.demo.DemoInterface;

public class SaveDemo implements DemoInterface {

	@Override
	public void startRun() {
		Level level = Level.HARD;
		String baseDir = "sodu" + "/hard";
		File fileDir = new File(baseDir);

		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File file = new File(baseDir + "/data_hard"
				+ "" );
		if (file.exists()) {
			file.delete();
		}
		if (!file.exists()) {
			boolean mkdirs = false;
			try {
				mkdirs = file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("mkdirs " + mkdirs);
		}
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			int generate = 0;
			while (generate < 500) {
				MySoduGenerator mySoduGenerator = new MySoduGenerator();
				mySoduGenerator.generateGame(level);
				mySoduGenerator.getNoNeedToSolve();
				if (mySoduGenerator.getNoNeedToSolve() > level.getMaxSum()) {
					continue;
				}
				if(mySoduGenerator.getNoNeedToSolve()<level.getMinSum()){
					continue;
				}
				String dataString = mySoduGenerator.getDataString();
				bufferedWriter.write(dataString + "\r\n");
				generate++;
				System.out.println("fileDir " + fileDir.getAbsolutePath());
				System.err.println("generate " + generate);
			}
			bufferedWriter.flush();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				fileOutputStream.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				outputStreamWriter.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				bufferedWriter.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

}
