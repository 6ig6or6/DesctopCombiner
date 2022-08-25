package com.example.desctopapp.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Combiner {
    public void combine(List<File> parts) throws IOException {
        Set<Path> set = new LinkedHashSet<>(parts.stream().map(File::toPath).sorted(new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                return extractInt(o1.toString()) - extractInt(o2.toString()); /*if parts list is unsorted,
                 this method will sort it in a correct way depending on parts' numbers*/
            }
            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                return Integer.parseInt(num);
            }
        }).toList());
        Path newPath = createNewPathWithAppropriateName(set);
        Path newFile = Files.createFile(newPath);
        transferAllBytesToNewPath(newFile, set);
    }

    private static Path createNewPathWithAppropriateName(Set<Path> set){
        Path randomName = (Path) set.toArray()[0];
        Path fileName = randomName.getFileName();
        String stringFileName = fileName.toString();
        String [] technicalArray = stringFileName.split("\\.");
        String newFileName = technicalArray[0]+"."+technicalArray[1];
        return Paths.get(randomName.getParent() +"\\"+ newFileName);
    }
    private static void transferAllBytesToNewPath(Path path, Set<Path> set){
        for (Path p:set) {
            byte[] bytes;
            try(BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(p));
                FileOutputStream fileOutputStream = new FileOutputStream(path.toFile(), true);
                //the fileOutputStream will append bytes, not rewrite them
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

                while (bufferedInputStream.available()>0){
                    bytes = new byte[bufferedInputStream.available()];
                    bufferedInputStream.read(bytes);
                    bufferedOutputStream.write(bytes);
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
