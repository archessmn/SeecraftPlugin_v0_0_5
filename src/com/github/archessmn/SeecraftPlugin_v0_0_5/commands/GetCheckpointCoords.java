package com.github.archessmn.SeecraftPlugin_v0_0_5.commands;

import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.DataStorageYml;
import com.github.archessmn.SeecraftPlugin_v0_0_5.storage.HavenCheckpointStorage;

public class GetCheckpointCoords {
    public static void getCheckpointCoords() {
        int senderX = (int) DataStorageYml.get().get("senderX");
        int senderY = (int) DataStorageYml.get().get("senderY");
        int senderZ = (int) DataStorageYml.get().get("senderZ");



        int checkpointX;
        int checkpointZ;

        int remainderX = senderX % 1000;
        if (senderX >= 0) {
            //Positive X
            if (remainderX < 500) {
                checkpointX = (senderX - remainderX) / 1000;
                DataStorageYml.get().set("checkpointX", checkpointX);
                HavenCheckpointStorage.save();
            } else {
                checkpointX = (senderX + (1000 - remainderX));
                DataStorageYml.get().set("checkpointX", checkpointX);
                HavenCheckpointStorage.save();
            }
        } else {
            //Negative X
            if (remainderX > (- 500)) {
                checkpointX = (senderX - remainderX) / 1000;
                DataStorageYml.get().set("checkpointX", checkpointX);
                HavenCheckpointStorage.save();
            } else {
                checkpointX = (senderX - (- remainderX)) / 1000;
                DataStorageYml.get().set("checkpointX", checkpointX);
                HavenCheckpointStorage.save();
            }
        }
        int remainderZ = senderZ % 1000;
        if (senderZ >= 0) {
            //Positive Z
            if (remainderZ < 500) {
                checkpointZ = (senderZ - remainderZ) /1000;
                DataStorageYml.get().set("checkpointZ", checkpointZ);
                HavenCheckpointStorage.save();
            } else {
                checkpointZ = (senderZ + (1000 - remainderZ)) / 1000;
                DataStorageYml.get().set("checkpointZ", checkpointZ);
                HavenCheckpointStorage.save();
            }
        } else {
            //Negative Z
            if (remainderZ > (- 500)) {
                checkpointZ = (senderZ - remainderZ) / 1000;
                DataStorageYml.get().set("checkpointZ", checkpointZ);
                HavenCheckpointStorage.save();
            } else {
                checkpointZ = (senderZ - (- remainderZ)) / 1000;
                DataStorageYml.get().set("checkpointZ", checkpointZ);
                HavenCheckpointStorage.save();
            }
        }
    }
}
