package br.com.fiap.bo;

public class PlanoBO {
	public String atualizarPlanoUsuario(String cpf, String tipoPlano) {
		if (!validarCPF(cpf)) {
	            return "CPF inválido. Atualização não permitida.";
	        }
	        
	    if (!validarTipoPlano(tipoPlano)) {
	            return "Tipo de plano inválido. Escolha: Gratuito, Premium ou Premium Plus.";
	        }

	        // Lógica para persistir o novo plano no banco
	        return "Plano atualizado com sucesso para o tipo: " + tipoPlano;
	    }

	    // Valida o CPF do usuário
	private boolean validarCPF(String cpf) {
		return cpf != null && cpf.matches("\\d{11}"); // Verifica se contém 11 dígitos
	    }

	    // Valida o tipo de plano
	private boolean validarTipoPlano(String tipoPlano) {
		return tipoPlano.equalsIgnoreCase("Gratuito") || 
	           tipoPlano.equalsIgnoreCase("Premium") || 
	           tipoPlano.equalsIgnoreCase("Premium Plus");
	    }
	}

