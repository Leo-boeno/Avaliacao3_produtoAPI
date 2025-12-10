const API = '/animais';

async function listar() {
  const res = await fetch(API);
  const dados = await res.json();
  const tbody = document.querySelector('#tabela tbody');
  tbody.innerHTML = '';

  dados.forEach(a => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${a.id}</td>
      <td>${a.nome}</td>
      <td>${a.idade ?? ''}</td>
      <td>${a.adocao}</td>
      <td>${a.chip ?? ''}</td>
      <td class="actions">
        <button onclick="editar(${a.id})">Editar</button>
        <button class="delete" onclick="remover(${a.id})">Excluir</button>
      </td>`;
    tbody.appendChild(tr);
  });
}

async function editar(id) {
  const res = await fetch(`${API}/${id}`);
  if (!res.ok) { alert('Erro ao buscar animal'); return; }

  const a = await res.json();

  document.getElementById('animalId').value = a.id;
  document.getElementById('nome').value = a.nome;
  document.getElementById('idade').value = a.idade ?? '';
  document.getElementById('adocao').value = a.adocao;
  document.getElementById('chip').value = a.chip ?? '';

  window.scrollTo({ top: 0, behavior: 'smooth' });
}

async function remover(id) {
  if (!confirm('Confirma exclusão?')) return;

  const res = await fetch(`${API}/${id}`, { method: 'DELETE' });

  if (res.status === 204) {
    listar();
  } else {
    alert('Erro ao excluir');
  }
}

document.getElementById('animalForm').addEventListener('submit', async function(e) {
  e.preventDefault();

  const id = document.getElementById('animalId').value;
  const payload = {
    nome: document.getElementById('nome').value,
    idade: document.getElementById('idade').value ? parseInt(document.getElementById('idade').value) : null,
    adocao: document.getElementById('adocao').value === 'true',
    chip: document.getElementById('chip').value || null
  };

  if (!payload.nome) {
    alert('Nome é obrigatório');
    return;
  }

  if (id) {
    const res = await fetch(`${API}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });

    if (res.ok) { limpar(); listar(); }
    else { alert('Erro ao atualizar'); }

  } else {
    const res = await fetch(API, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });

    if (res.ok) { limpar(); listar(); }
    else { alert('Erro ao salvar'); }
  }
});

document.getElementById('limpar').addEventListener('click', limpar);

function limpar() {
  document.getElementById('animalId').value = '';
  document.getElementById('nome').value = '';
  document.getElementById('idade').value = '';
  document.getElementById('adocao').value = 'true';
  document.getElementById('chip').value = '';
}

// inicial
listar();
