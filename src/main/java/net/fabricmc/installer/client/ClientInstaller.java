/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.installer.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;

import mjson.Json;

import net.fabricmc.installer.LoaderVersion;
import net.fabricmc.installer.util.FabricService;
import net.fabricmc.installer.util.InstallerProgress;
import net.fabricmc.installer.util.Library;
import net.fabricmc.installer.util.Reference;
import net.fabricmc.installer.util.Utils;

import javax.swing.*;

public class ClientInstaller {
	public static String install(Path mcDir, String gameVersion, LoaderVersion loaderVersion, InstallerProgress progress) throws IOException {
		System.out.println("Installing " + gameVersion + " with fabric " + loaderVersion.name);

		String profileName = String.format("%s-%s-%s", Reference.LOADER_NAME, loaderVersion.name, gameVersion);

		Path versionsDir = mcDir.resolve("versions");
		Path profileDir = versionsDir.resolve(profileName);
		String name = String.format("fabric-loader-%s-1.6.4-MITE", loaderVersion.name);
		Path profileJson = profileDir.resolve(name + ".json");
		if (!Files.exists(profileDir)) {
			Files.createDirectories(profileDir);
		}

		Path profileJar = profileDir.resolve(profileName + ".jar");
		Files.deleteIfExists(profileJar);

		String string = getString(ClientInstaller.class.getResourceAsStream("/config.json"));
		string = string.replace("${loaderVersion}", loaderVersion.name);
		Files.write(profileJson, string.getBytes(StandardCharsets.UTF_8));

		/*
		Downloading the libraries isn't strictly necessary as the launcher will do it for us.
		Do it anyway in case the launcher fails, we know we have a working connection to maven here.
		 */
		Path libsDir = mcDir.resolve("libraries");

//		for (Json libraryJson : json.at("libraries").asJsonList()) {
//			Library library = new Library(libraryJson);
//			Path libraryFile = libsDir.resolve(library.getPath());
//			String url = library.getURL();
//
//			//System.out.println("Downloading "+url+" to "+libraryFile);
//			progress.updateProgress(new MessageFormat(Utils.BUNDLE.getString("progress.download.library.entry")).format(new Object[]{library.name}));
//			FabricService.downloadSubstitutedMaven(url, libraryFile);
//		}

		progress.updateProgress(Utils.BUNDLE.getString("progress.done"));

		return profileName;
	}

	private static String getString(InputStream inputStream) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line).append("\n");
		}
		return stringBuilder.toString();
	}

}
