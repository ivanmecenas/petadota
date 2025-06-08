package br.com.mecenas.petadota.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

import br.com.mecenas.petadota.PetAdotaApplication;
import br.com.mecenas.petadota.model.Animal;
import br.com.mecenas.petadota.controller.AnimalController;
import br.com.mecenas.petadota.repository.AnimalRepository;
import br.com.mecenas.petadota.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;

public class PetAdotaView extends JFrame {

    // criação dos componentes da interface gráfica
    private JTextField idField;
    private JTextField nomeField;
    private JTextField tipoField;
    private JTextField racaField;
    private JTextField idadeField;
    private JTextField descricaoField;
    private JTextField campoPesquisa;
    private JButton botaoCadastrarDados;
    private JButton botaoLerDados;
    private JButton botaoAtualizarDados;
    private JButton botaoDeletarDados;
    private JButton botaoPesquisa;
    private JTable tabelaDados;
    private JLabel logomarca;

    // injetando o AnimalController, AnimalRepository e AnimalService
    private AnimalController animalController;

    @Autowired
    private static AnimalRepository animalRepository;

    @Autowired
    private static AnimalService animalService;

    // inicializando a interface gráfica
    public PetAdotaView(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
        this.animalService = new AnimalService(animalRepository);
        animalController = new AnimalController(animalService);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("PetAdota - Adote um Pet");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon petadota_logo = new ImageIcon(getClass().getResource("/images/petadota_logo.png"));
        ImageIcon petadota_logo_menor = new ImageIcon(petadota_logo.getImage().getScaledInstance(
                (int) (petadota_logo.getIconWidth() * 0.3),
                (int) (petadota_logo.getIconHeight() * 0.3),
                Image.SCALE_SMOOTH));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel logoLabel = new JLabel(petadota_logo_menor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(logoLabel, gbc);

        JLabel idLabel = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(idLabel, gbc);
        idField = new JTextField(5);
        idField.setEditable(false);
        gbc.gridx = 1;
        inputPanel.add(idField, gbc);
    
        JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(nomeLabel, gbc);
        nomeField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(nomeField, gbc);

        JLabel tipoLabel = new JLabel("Tipo:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(tipoLabel, gbc);
        tipoField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(tipoField, gbc);

        JLabel racaLabel = new JLabel("Raça:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(racaLabel, gbc);
        racaField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(racaField, gbc);
    
        JLabel idadeLabel = new JLabel("Idade:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(idadeLabel, gbc);
        idadeField = new JTextField(3);
        gbc.gridx = 1;
        inputPanel.add(idadeField, gbc);
    
        JLabel descricaoLabel = new JLabel("Descrição:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(descricaoLabel, gbc);
        descricaoField = new JTextField(40);
        gbc.gridx = 1;
        inputPanel.add(descricaoField, gbc);
    
        mainPanel.add(inputPanel, BorderLayout.NORTH);
    
        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botaoCadastrarDados = new JButton("Criar");
        botaoLerDados = new JButton("Listar");
        botaoAtualizarDados = new JButton("Atualizar");
        botaoDeletarDados = new JButton("Deletar");

        botaoAtualizarDados.setEnabled(false);
        botaoDeletarDados.setEnabled(false);

        buttonPanel.add(botaoCadastrarDados);
        buttonPanel.add(botaoLerDados);
        buttonPanel.add(botaoAtualizarDados);
        buttonPanel.add(botaoDeletarDados);
    
        // Painel de pesquisa por ID, Tipo ou Raça
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        campoPesquisa = new JTextField(20);
        botaoPesquisa = new JButton("Pesquisar");

        searchPanel.add(new JLabel("Pesquisar por ID, Tipo ou Raça:"));
        searchPanel.add(campoPesquisa);
        searchPanel.add(botaoPesquisa);


        // Painel combinado para botões e pesquisa
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));
        combinedPanel.add(buttonPanel);
        combinedPanel.add(searchPanel);

        mainPanel.add(combinedPanel, BorderLayout.CENTER);

        // Tabela de pets
        tabelaDados = new JTable();
        JScrollPane tableScroll = new JScrollPane(tabelaDados);
        tableScroll.setPreferredSize(new Dimension(650, 210));

        mainPanel.add(tableScroll, BorderLayout.SOUTH);
        add(mainPanel);

        // Adiciona os eventos dos botões e da tabela
        botaoCadastrarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarAnimal();
            }
        });

        botaoLerDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAnimal();
            }
        });

        botaoAtualizarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAnimal();
            }
        });

        botaoDeletarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado = JOptionPane.showConfirmDialog(null,
                        "Confirma excluir este animal?",
                        "Excluir Animal",
                        JOptionPane.YES_NO_OPTION);

                if (resultado == JOptionPane.YES_OPTION){
                    deletarAnimal();
                } else {
                    listarAnimal();
                }

            }
        });

        botaoPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemBusca = campoPesquisa.getText().toLowerCase();
                List<Animal> animais = animalController.getAllAnimais();
                List<Animal> filteredAnimais = animais.stream()
                        .filter(animal -> animal.getId().toString().contains(itemBusca) ||
                                animal.getTipo().toLowerCase().contains(itemBusca) ||
                                animal.getRaca().toLowerCase().contains(itemBusca))
                        .toList();

                String[] colunas = {"ID", "Nome", "Tipo", "Raça", "Idade", "Descrição"};
                Object[][] dados = new Object[filteredAnimais.size()][6];
                for (int i = 0; i < filteredAnimais.size(); i++) {
                    Animal a = filteredAnimais.get(i);
                    dados[i][0] = a.getId();
                    dados[i][1] = a.getNome();
                    dados[i][2] = a.getTipo();
                    dados[i][3] = a.getRaca();
                    dados[i][4] = a.getIdade();
                    dados[i][5] = a.getDescricao();
                }
                tabelaDados.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
            }
        });

        tabelaDados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = tabelaDados.getSelectedRow();
                if (row != -1) {
                    idField.setText(tabelaDados.getValueAt(row, 0).toString());
                    nomeField.setText(tabelaDados.getValueAt(row, 1).toString());
                    tipoField.setText(tabelaDados.getValueAt(row, 2).toString());
                    racaField.setText(tabelaDados.getValueAt(row, 3).toString());
                    idadeField.setText(tabelaDados.getValueAt(row, 4).toString());
                    descricaoField.setText(tabelaDados.getValueAt(row, 5).toString());

                    botaoCadastrarDados.setEnabled(false);
                    botaoAtualizarDados.setEnabled(true);
                    botaoDeletarDados.setEnabled(true);
                }
            }
        });

        // Carrega os dados iniciais
        listarAnimal();
    }

    // Métodos para manipulação de dados
    private void criarAnimal() {
        String nome = nomeField.getText();
        String tipo = tipoField.getText();
        String raca = racaField.getText();
        int idade = 0;
        if (!idadeField.getText().isEmpty()) {
            idade = Integer.parseInt(idadeField.getText());
        }
        String descricao = descricaoField.getText();

        if (nome.isEmpty() || tipo.isEmpty() || raca.isEmpty() || descricao.isEmpty() || idadeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Animal animal = new Animal();
        animal.setNome(nome);
        animal.setTipo(tipo);
        animal.setRaca(raca);
        animal.setIdade(idade);
        animal.setDescricao(descricao);
        animalController.criarAnimal(animal);

        listarAnimal();

        botaoCadastrarDados.setEnabled(true);
        botaoAtualizarDados.setEnabled(false);
        botaoDeletarDados.setEnabled(false);
    }

    private void listarAnimal() {
        List<Animal> animais = animalController.getAllAnimais();
        String[] colunas = {"ID", "Nome", "Tipo", "Raça", "Idade", "Descrição"};
        Object[][] dados = new Object[animais.size()][6];
        for (int i = 0; i < animais.size(); i++) {
            Animal a = animais.get(i);
            dados[i][0] = a.getId();
            dados[i][1] = a.getNome();
            dados[i][2] = a.getTipo();
            dados[i][3] = a.getRaca();
            dados[i][4] = a.getIdade();
            dados[i][5] = a.getDescricao();
        }
        tabelaDados.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
        tabelaDados.getColumnModel().getColumn(0).setPreferredWidth(3);
        tabelaDados.getColumnModel().getColumn(1).setPreferredWidth(10);
        tabelaDados.getColumnModel().getColumn(2).setPreferredWidth(5);
        tabelaDados.getColumnModel().getColumn(3).setPreferredWidth(10);
        tabelaDados.getColumnModel().getColumn(4).setPreferredWidth(5);
        tabelaDados.getColumnModel().getColumn(5).setPreferredWidth(20);

        tabelaDados.isCellEditable(0,0); // Tabela não editável

        limparCampos();

        botaoCadastrarDados.setEnabled(true);
        botaoAtualizarDados.setEnabled(false);
        botaoDeletarDados.setEnabled(false);
    }

    private void atualizarAnimal() {
        Long id = Long.parseLong(idField.getText());
        String nome = nomeField.getText();
        String tipo = tipoField.getText();
        String raca = racaField.getText();
        int idade = Integer.parseInt(idadeField.getText());
        String descricao = descricaoField.getText();

        Animal animal = new Animal();
        animal.setId(id);
        animal.setNome(nome);
        animal.setTipo(tipo);
        animal.setRaca(raca);
        animal.setIdade(idade);
        animal.setDescricao(descricao);

        animalController.atualizarAnimal(id, animal);

        listarAnimal();
    }

    private void deletarAnimal() {
        Long id = Long.parseLong(idField.getText());
        animalController.deletarAnimal(id);
        listarAnimal();
    }

    private void limparCampos() {
        idField.setText("");
        nomeField.setText("");
        tipoField.setText("");
        racaField.setText("");
        idadeField.setText("");
        descricaoField.setText("");
        campoPesquisa.setText("");
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        org.springframework.context.ApplicationContext context = org.springframework.boot.SpringApplication.run(PetAdotaApplication.class, args);
        AnimalRepository animalRepository = context.getBean(AnimalRepository.class);

        SwingUtilities.invokeLater(() -> {
            PetAdotaView view = new PetAdotaView(animalRepository);
                view.setVisible(true);
        });
    }
}