defmodule Huffman do
# ctrl + g, i, c to end forever recursive function in iex

  def sample() do
    'who made this burger. jason, your killing it man'
  end

  def test do
    sample = sample()
    tree = tree(sample)
    encode = encode_table(tree)
  #  decode = decode_table(tree)
  #  text = text()
  #  seq = encode(text, encode)
  #  decode(seq, decode)
  end

  def tree(sample) do
    freq = freq(sample)
    huffman(freq)
  end
  def huffman(freq) do
    sorted = Enum.sort(freq, fn({_, x},{_, y}) -> x < y end)
    huffman_tree(sorted)
  end

  #frequency
  def freq(sample) do freq(sample, []) end
  def freq([], freq) do freq end
  def freq([char | rest], freq) do freq(rest, update(char, freq)) end

  #update frequency
  def update(char, []) do [{char, 1}] end
  def update(char, [{char, n} | freq]) do [{char, n + 1} | freq] end
  def update(char, [elem | freq]) do [elem | update(char, freq)] end

  #huffman tree
  def huffman_tree([{tree, _}]) do tree end
  def huffman_tree([{a, af}, {b, bf} | rest]) do huffman_tree(insert({{a, b}, af + bf}, rest)) end
  #inserting in huffman tree
  def insert({a, af}, []) do [{a, af}] end
  def insert({a, af}, [{b, bf} | rest]) when af < bf do [{a, af}, {b, bf} | rest] end
  def insert({a, af}, [{b, bf} | rest]) do [{b, bf} | insert({a, af}, rest)] end
  #use the huffman tree to create a table with every characters code
  def encode_table(tree) do traverse_code(tree, []) end
  #use traverse_code to
  def traverse_code({a,b}, sofar) do
    left = traverse_code(a, [0 | sofar])
    right = traverse_code(b, [1 | sofar])
    left ++ right
  end
  def traverse_code(a, code) do [{a, Enum.reverse(code)}] end

  def decode_table(tree) do traverse_code(tree, []) end

  def encode([], _) do [] end
  def encode([char | rest], table) do
    {_, code} = List.keyfind(table, char, 0)
    code ++ encode(rest, table)
  end

  def decode([], _) do [] end
  def decode(seq, tree) do
    {char, rest} = decode_char(seq, 1, tree)
    [char | decode(rest, tree)]
  end
  def decode_char(seq, n, table) do
    {code, rest} = Enum.split(seq, n)
    case List.keyfind(table, code, 1) do
      {char, _} -> {char, rest}

      nil -> decode_char(seq, n + 1, table)
    end
  end
end
