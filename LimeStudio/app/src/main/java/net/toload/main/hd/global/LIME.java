/*
 *
 *  *
 *  **    Copyright 2015, The LimeIME Open Source Project
 *  **
 *  **    Project Url: http://github.com/lime-ime/limeime/
 *  **                 http://android.toload.net/
 *  **
 *  **    This program is free software: you can redistribute it and/or modify
 *  **    it under the terms of the GNU General Public License as published by
 *  **    the Free Software Foundation, either version 3 of the License, or
 *  **    (at your option) any later version.
 *  *
 *  **    This program is distributed in the hope that it will be useful,
 *  **    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  **    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  **    GNU General Public License for more details.
 *  *
 *  **    You should have received a copy of the GNU General Public License
 *  **    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  *
 *
 */

package net.toload.main.hd.global;

import android.os.Environment;

public class LIME {
	public static String PACKAGE_NAME;

	public static final String LIME_SDCARD_FOLDER = Environment.getExternalStorageDirectory() + "/limehd/";
	public static String getLimeDataRootFolder(){ return Environment.getDataDirectory() + "/data/"+LIME.PACKAGE_NAME; }
	public static final String DATABASE_RELATIVE_FOLDER = "/databases";
	public static String getLIMEDatabaseFolder(){ return  Environment.getDataDirectory() + "/data/"+LIME.PACKAGE_NAME + LIME.DATABASE_RELATIVE_FOLDER;};
	public static final String DATABASE_NAME = "lime.db";
	public static final String DATABASE_JOURNAL = "lime.db-journal";
	public static final String DATABASE_DECOMPRESS_FOLDER_SDCARD = Environment.getExternalStorageDirectory() + "/limehd";
	public static final String DATABASE_BACKUP_NAME = "backup.zip";
	public static final String DATABASE_JOURNAL_BACKUP_NAME = "backupJournal.zip";
	public static final String SHARED_PREFS_BACKUP_NAME=  "shared_prefs.bak";
	public static final String DATABASE_CLOUD_TEMP = "cloudtemp.zip";
	public static final String IM_CJ_STATUS = "im_cj_status";
	public static final String IM_SCJ_STATUS = "im_scj_status";
	public static final String IM_PHONETIC_STATUS = "im_phonetic_status";
	public static final String IM_DAYI_STATUS = "im_dayi_status";
	public static final String IM_CUSTOM_STATUS = "im_custom_status";
	public static final String IM_EZ_STATUS = "im_ez_status";
	
	public static final String IM_MAPPING_FILENAME = "im_mapping_filename";
	public static final String IM_MAPPING_VERSION = "im_mapping_version";
	public static final String IM_MAPPING_TOTAL = "im_mapping_total";
	public static final String IM_MAPPING_DATE = "im_mapping_date";

	public static final String CANDIDATE_SUGGESTION = "candidate_suggestion";
	public static final String TOTAL_USERDICT_RECORD = "total_userdict_record";
	public static final String LEARNING_SWITCH = "learning_switch";

	


	//public final static String SEARCHSRV_RESET_CACHE = "searchsrv_reset_cache";
	public final static int SEARCHSRV_RESET_CACHE_SIZE = 256;
	public final static int LIMEDB_CACHE_SIZE = 1024;

	// ADMOB
	public final static String publisher = "ca-app-pub-6429718170873338/7028669804";

}
