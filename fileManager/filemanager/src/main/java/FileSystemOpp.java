import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileSystemOpp {
    public boolean renameFile(String oldFileName, String newFileName) {
        File oldFile = new File(oldFileName);
        File newFile = new File(newFileName);
        if (newFile.exists()) {
            return false;
        }
        boolean success = oldFile.renameTo(newFile);
        if (!success) {
            return false;
        } else {
            return true;
        }
    }
    public List<FileDTO> ListDirectory(String path) throws IOException {
        List<FileDTO> file=new ArrayList<>();
        return recursiveListDirectory(file,path);
    }
    private List<FileDTO> recursiveListDirectory (List<FileDTO>files , String path) throws IOException {
        File file=new File(path);
        Path source= Paths.get(path);
        BasicFileAttributes attr=Files.readAttributes(source,BasicFileAttributes.class);
        if (file.isDirectory()) {
            FileDTO temp=new FileDTO();
            temp.setFileName(file.getName());
            temp.setAddress(file.getPath());
            temp.setLastModified(file.lastModified());
            temp.setFileSize(Files.size(source));
            temp.isADirectory();
            temp.setCreateTime(attr.creationTime().toMillis());
            files.add(temp);
            for (File f :file.listFiles()) {
                if (f.isDirectory()) {
                    recursiveListDirectory(files, f.getPath());
                }
            }
        } else {
            return files;
        }
        return files;
    }
    public boolean copyFile(String inputPath,String newPath)throws Exception {
        boolean result = true;
        if (!(inputPath.isEmpty() || newPath.isEmpty())) {
            File sourceFile = new File(inputPath);
            String sourceFileName = sourceFile.getName();
            Path source = Paths.get(inputPath);
            String destinationFolderName;
            File destinationFolder = new File(newPath);
            if (destinationFolder.isDirectory()) {
                destinationFolderName = destinationFolder.getPath();
            } else {
                destinationFolderName = destinationFolder.getParent();
            }
            String destinationFileName = destinationFolderName + "\\" + sourceFileName;
            if (!Files.exists(Paths.get(destinationFileName))) {
                Path destination = Paths.get(destinationFileName);
                Files.copy(source, destination);
            }
        } else {
            result = false;
        }
        return result;
    }
    public List<FileDTO> getAllfilesOfDirectory(String path)throws Exception{
        List<FileDTO> fileDTOList =new ArrayList<>();
        return recursiveDirectory(fileDTOList, path);
    }
    private List<FileDTO> recursiveDirectory(List<FileDTO> fileDTOList, String path)throws Exception{
        File file= new File(path);
        BasicFileAttributes attributes=Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
        if(file.isDirectory()){
            for(File f: file.listFiles()){
                if(f.isDirectory()){
                    recursiveDirectory(fileDTOList, f.getPath());
                }else{
                    FileDTO temp=new FileDTO();
                    temp.setFileName(f.getName());
                    temp.setLastModified(f.lastModified());
                    temp.setAddress(f.getPath());
                    temp.setCreateTime(attributes.creationTime().to(TimeUnit.MILLISECONDS));
                    temp.setFileSize(Files.size(Paths.get(path)));
                    fileDTOList.add(temp);
                }
            }
        }else{
            FileDTO temp=new FileDTO();
            temp.setFileName(file.getName());
            temp.setLastModified(file.lastModified());
            temp.setAddress(file.getPath());
            temp.setCreateTime(attributes.creationTime().to(TimeUnit.MILLISECONDS));
            temp.setFileSize(Files.size(Paths.get(path)));
            fileDTOList.add(temp);
        }
        return fileDTOList;
    }



    public boolean isDtoValid(FileDTO dto)  {

        String fileAddress=dto.getAddress();
        File file = new File(fileAddress);
        String path = file.getPath();
        Path filePath = Paths.get(path);
        BasicFileAttributes attr=null;
        try {
             attr = Files.readAttributes(filePath, BasicFileAttributes.class);
        }catch (Exception e){
            return false;
        }
        if (file.lastModified()==dto.getLastModified()){
            if (attr.size()==dto.getFileSize()){
                if(file.getName().equals(dto.getFileName())){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean deleteFile(String address) {
        File file = new File(address);
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteDirectory(String address) throws IOException {
        Path directory = Paths.get(address);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
        return true;
    }
    public FileDTO getFileDTOOfFile(String address){
        File file=new File(address);
        FileDTO fileDTO=new FileDTO();
        BasicFileAttributes view;
        try {
            view = Files.readAttributes(Paths.get(address), BasicFileAttributes.class);
        }catch (Exception e){
            throw new RuntimeException("address dose't exist");
        }
        if(file.isDirectory()){
            fileDTO.setFileName(file.getName());
            fileDTO.setLastModified(file.lastModified());
            fileDTO.setAddress(file.getPath());
            fileDTO.setFileSize(view.size());
            fileDTO.setCreateTime(view.creationTime().toMillis());
        }
        return fileDTO;
    }
    public List<FileDTO> displayDirectoryContents(File dir) {
        File[] files = dir.listFiles();
        ArrayList<FileDTO> directory= new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                directory.add(getFileDTOOfFile(file.getPath()));
            }
        }
        return directory;
    }
    public  void  creatNewDirectory(String adress) {
        File file=new File(adress);
        file.mkdir();
    }
    public  ArrayList<FileDTO> firstLevelListFile(String adress)  {
        ArrayList<FileDTO> fileList=new ArrayList<FileDTO>();
        File file=new File(adress);
        Path filePath= Paths.get(adress);
        BasicFileAttributes attributes;
        try {
            attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
        }catch (Exception e){
            throw new RuntimeException("address dose't exist");
        }
        for (File filesInFolder:file.listFiles()){
            if(filesInFolder.isFile()){
                FileDTO DTO=new FileDTO();
                DTO.setAddress(filesInFolder.getPath());
                DTO.setFileName(filesInFolder.getName());
                DTO.setFileSize(filesInFolder.length());
                DTO.setLastModified(filesInFolder.lastModified());
                DTO.setCreateTime(attributes.creationTime().toMillis());
                fileList.add(DTO);
            }
        }
        return fileList;
    }
}

