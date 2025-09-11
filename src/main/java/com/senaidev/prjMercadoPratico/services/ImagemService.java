package com.senaidev.prjMercadoPratico.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ImagemService {
    
    // Diretório onde as imagens serão salvas
    @Value("${upload.path:uploads/produtos/}")
    private String uploadPath;
    
    // Tipos de arquivo permitidos
    private final List<String> tiposPermitidos = Arrays.asList(
        "image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp"
    );
    
    // Tamanho máximo (5MB)
    private final long tamanhoMaximo = 5 * 1024 * 1024;
    
    public String salvarImagem(MultipartFile arquivo) throws IOException {
        // Validações
        validarArquivo(arquivo);
        
        // Criar diretório se não existir
        Path diretorio = Paths.get(uploadPath);
        if (!Files.exists(diretorio)) {
            Files.createDirectories(diretorio);
        }
        
        // Gerar nome único para o arquivo
        String nomeOriginal = arquivo.getOriginalFilename();
        String extensao = obterExtensao(nomeOriginal);
        String nomeUnico = UUID.randomUUID().toString() + extensao;
        
        // Caminho completo do arquivo
        Path caminhoArquivo = diretorio.resolve(nomeUnico);
        
        // Salvar arquivo
        Files.copy(arquivo.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);
        
        return nomeUnico;
    }
    
    public void deletarImagem(String nomeArquivo) {
        try {
            if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
                Path arquivo = Paths.get(uploadPath).resolve(nomeArquivo);
                Files.deleteIfExists(arquivo);
            }
        } catch (IOException e) {
            // Log do erro mas não falha a operação
            System.err.println("Erro ao deletar imagem: " + e.getMessage());
        }
    }
    
    public boolean imagemExiste(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            return false;
        }
        Path arquivo = Paths.get(uploadPath).resolve(nomeArquivo);
        return Files.exists(arquivo);
    }
    
    private void validarArquivo(MultipartFile arquivo) throws IOException {
        if (arquivo.isEmpty()) {
            throw new IOException("Arquivo está vazio");
        }
        
        if (arquivo.getSize() > tamanhoMaximo) {
            throw new IOException("Arquivo muito grande. Máximo permitido: 5MB");
        }
        
        String tipoConteudo = arquivo.getContentType();
        if (tipoConteudo == null || !tiposPermitidos.contains(tipoConteudo.toLowerCase())) {
            throw new IOException("Tipo de arquivo não permitido. Use: JPEG, PNG, GIF ou WebP");
        }
    }
    
    private String obterExtensao(String nomeArquivo) {
        if (nomeArquivo == null || !nomeArquivo.contains(".")) {
            return ".jpg"; // extensão padrão
        }
        return nomeArquivo.substring(nomeArquivo.lastIndexOf(".")).toLowerCase();
    }
    
    public String obterCaminhoCompleto(String nomeArquivo) {
        return uploadPath + nomeArquivo;
    }
}