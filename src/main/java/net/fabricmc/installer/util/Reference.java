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

package net.fabricmc.installer.util;

public class Reference {
	public static final String LOADER_NAME = "fabric-loader";
	public static final String LEGACY_FABRIC_API_URL = "https://github.com/Legacy-Fabric/fabric/releases/";
	public static final String MINECRAFT_LAUNCHER_MANIFEST = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json";
	public static final String OLD_SNAPSHOTS_MANIFEST = "https://raw.githubusercontent.com/Legacy-Fabric/manifests/master/manifest.json";
	public static final String LEGACY_FABRIC_META = "https://meta.legacyfabric.net/";
	public static final String FABRIC_MAVEN = "https://maven.fabricmc.net/";
	public static final String LEGACY_FABRIC_MAVEN = "https://maven.legacyfabric.net/";

	static final FabricService[] FABRIC_SERVICES = {
			new FabricService(LEGACY_FABRIC_META, LEGACY_FABRIC_MAVEN)//,
			// Do not use these fallback servers to interact with our web services. They can and will be unavailable at times and only support limited throughput.
//			new FabricService("https://meta2.fabricmc.net/", "https://maven2.fabricmc.net/"),
//			new FabricService("https://meta3.fabricmc.net/", "https://maven3.fabricmc.net/")
	};
}
