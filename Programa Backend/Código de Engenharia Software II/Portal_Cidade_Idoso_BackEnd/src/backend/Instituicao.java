package backend;

import java.util.ArrayList;
import java.util.List;

public class Instituicao {
    private int idInstituicao;
    private String orgaoResponsavel;
    private List<Contato> contatos; // Instituicao tem contatos
    private Endereco endereco; // Instituicao possui um endereço
    private static int proximoId = 1;

    public Instituicao(String orgaoResponsavel, Endereco endereco) {
        this.idInstituicao = proximoId++;
        this.orgaoResponsavel = orgaoResponsavel;
        this.endereco = endereco;
        this.contatos = new ArrayList<>();
    }

    // Getters
    public int getIdInstituicao() { return idInstituicao; }
    public String getOrgaoResponsavel() { return orgaoResponsavel; }
    public Endereco getEndereco() { return endereco; }
    public List<Contato> getContatos() { return contatos; }

    // Setters
    public void setOrgaoResponsavel(String orgaoResponsavel) { this.orgaoResponsavel = orgaoResponsavel; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    // Métodos para gerenciar contatos
    public void adicionarContato(Contato contato) {
        this.contatos.add(contato);
        System.out.println("Contato " + contato.getDescricao() + " adicionado à Instituição " + orgaoResponsavel);
    }

    public void removerContato(Contato contato) {
        if (this.contatos.remove(contato)) {
            System.out.println("Contato " + contato.getDescricao() + " removido da Instituição " + orgaoResponsavel);
        } else {
            System.out.println("Contato não encontrado na Instituição.");
        }
    }

    @Override
    public String toString() {
        return "ID: " + idInstituicao + ", Órgão Responsável: " + orgaoResponsavel + ", Endereço: " + endereco;
    }
}