package com.zegoggles.smssync.service;

import android.support.annotation.NonNull;
import com.zegoggles.smssync.contacts.ContactGroup;
import com.zegoggles.smssync.mail.BackupImapStore;
import com.zegoggles.smssync.mail.DataType;

import java.util.EnumSet;

public class BackupConfig {
    public final BackupImapStore imapStore;
    public final boolean skip;
    public final int currentTry;
    public final int maxItemsPerSync;
    public final ContactGroup groupToBackup;
    public final BackupType backupType;
    public final boolean debug;
    public final EnumSet<DataType> typesToBackup;

    public BackupConfig(@NonNull BackupImapStore imapStore,
                        int currentTry,
                        boolean skip,
                        int maxItemsPerSync,
                        @NonNull ContactGroup groupToBackup,
                        @NonNull BackupType backupType,
                        @NonNull EnumSet<DataType> typesToBackup,
                        boolean debug) {
        if (imapStore == null) throw new IllegalArgumentException("need imapstore");
        if (typesToBackup == null || typesToBackup.isEmpty()) throw new IllegalArgumentException("need to specify types to backup");
        if (currentTry < 0) throw new IllegalArgumentException("currentTry < 0");

        this.imapStore = imapStore;
        this.skip = skip;
        this.currentTry = currentTry;
        this.maxItemsPerSync = maxItemsPerSync;
        this.groupToBackup = groupToBackup;
        this.backupType = backupType;
        this.debug = debug;
        this.typesToBackup = typesToBackup;
    }

    public BackupConfig retryWithStore(BackupImapStore store) {
        return new BackupConfig(store, currentTry + 1,
                skip,
                maxItemsPerSync,
                groupToBackup,
                backupType,
                typesToBackup, debug);
    }


    @Override public String toString() {
        return "BackupConfig{" +
                "imap=" + imapStore +
                ", skip=" + skip +
                ", currentTry=" + currentTry +
                ", maxItemsPerSync=" + maxItemsPerSync +
                ", groupToBackup=" + groupToBackup +
                ", backupType=" + backupType +
                ", debug=" + debug +
                ", typesToBackup=" + typesToBackup +
                '}';
    }
}
