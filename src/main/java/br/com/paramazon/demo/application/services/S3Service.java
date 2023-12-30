package br.com.paramazon.demo.application.services;

import br.com.paramazon.demo.config.properties.S3Properties;
import com.amazonaws.auth.*;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
@Slf4j
@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    @Autowired
    private final S3Properties s3Properties;

    private final static long tenYearsInSeconds = 10 * 365 * 24 * 60 * 60L;

    public S3Service(S3Properties s3Properties) {
        this.s3Properties = s3Properties;
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(s3Properties.getAccessKeyId(), s3Properties.getSecretKeyId()));

        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(s3Properties.getRegion())
                .withCredentials(credentialsProvider)
                .build();
    }

    @SneakyThrows
    public String saveBucket(String id, String typeArchive, File file, String prefix) {
        log.info("S3Service :: Iniciando a persistencia do arquivo no bucket");
        String defaultBucket = s3Properties.getBucket();
        log.info("S3Service :: Salvando arquivo no Bucket: " + defaultBucket);
        String storedFilePath = prefix + "_" + typeArchive + "_" + id;
        log.info("S3Service :: Nome do arquivo: " + storedFilePath);
        return saveS3BucketByFile(file, storedFilePath, defaultBucket);
    }

    @SneakyThrows(FileNotFoundException.class)
    private String saveS3BucketByFile(File file, String storedFilePath, String defaultBucket) {
        log.info("S3Service :: Iniciando a persistencia da imagem fornecida no bucket");
        amazonS3.putObject(new PutObjectRequest(defaultBucket, storedFilePath, new FileInputStream(file), getMetadata(file)).withCannedAcl(CannedAccessControlList.PublicRead));
        URL s3Url = amazonS3.getUrl(s3Properties.getBucket(), storedFilePath);
        log.info("S3Service :: Imagem salva no bucket com sucesso!");
        return s3Url.toString();
    }

    @SneakyThrows(IOException.class)
    private ObjectMetadata getMetadata(File file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setCacheControl("max-age=" + tenYearsInSeconds);
        metadata.setContentType(new Tika().detect(file));
        metadata.setContentLength(file.length());
        return metadata;
    }
}
