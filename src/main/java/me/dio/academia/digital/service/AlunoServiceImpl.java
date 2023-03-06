package me.dio.academia.digital.service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setBairro(form.getBairro());
        aluno.setCpf(form.getCpf());
        aluno.setNome(form.getNome());
        aluno.setDataDeNascimento(form.getDataDeNascimento());

        Aluno alunoSalvo = alunoRepository.save(aluno);

        return alunoSalvo;
    }

    @Override
    public Aluno get(Long id) {
        return null;
    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if(dataDeNascimento == null){
            return alunoRepository.findAll();
        }

        return alunoRepository.findByDataDeNascimento(LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER));
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisica(Long idAluno) throws RuntimeException{
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new RuntimeException("Erro ao buscar avaliacaoes aluno"));
        return aluno.getAvaliacoes();
    }

}
