package com.gklijs.adventofcode.day3;

class Patch {

    final int id;
    final int fromLeft;
    final int fromTop;
    final int width;
    final int height;

    Patch(String patch) {
        int startId = patch.indexOf('#') + 1;
        int startFromLeft = patch.indexOf('@') + 2;
        int startFromTop = patch.indexOf(',') + 1;
        int startWidth = patch.indexOf(':') + 2;
        int startHeight = patch.indexOf('x') + 1;
        id = Integer.parseInt(patch.substring(startId, startFromLeft - 3));
        fromLeft = Integer.parseInt(patch.substring(startFromLeft, startFromTop - 1));
        fromTop = Integer.parseInt(patch.substring(startFromTop, startWidth - 2));
        width = Integer.parseInt(patch.substring(startWidth, startHeight - 1));
        height = Integer.parseInt(patch.substring(startHeight));
    }
}
