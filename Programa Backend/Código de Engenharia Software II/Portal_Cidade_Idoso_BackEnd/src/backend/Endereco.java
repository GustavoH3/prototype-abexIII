package backend;

public class Endereco {
    private int idEndereco;
    private String cidade;
    private int numero;
    private String rua;
    private String cep; // Alterado para String para lidar com zeros à esquerda
    private String complemento;
    private String bairro;
    private static int proximoId = 1;

    public Endereco(String cidade, int numero, String rua, String cep, String complemento, String bairro) {
        this.idEndereco = proximoId++;
        this.cidade = cidade;
        this.numero = numero;
        this.rua = rua;
        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
    }

    // Getters
    public int getIdEndereco() { return idEndereco; }
    public String getCidade() { return cidade; }
    public int getNumero() { return numero; }
    public String getRua() { return rua; }
    public String getCep() { return cep; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }

    // Setters (para permitir edição)
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setRua(String rua) { this.rua = rua; }
    public void setCep(String cep) { this.cep = cep; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    @Override
    public String toString() {
        return "ID: " + idEndereco + ", Rua: " + rua + ", Nº: " + numero + ", Bairro: " + bairro + ", Cidade: " + cidade + ", CEP: " + cep + (complemento != null && !complemento.isEmpty() ? ", Compl.: " + complemento : "");
    }
}